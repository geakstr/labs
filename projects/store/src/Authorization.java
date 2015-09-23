import sun.jvm.hotspot.debugger.AddressException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Предоставляет функции авторизации
 *
 * @since 12/3/12, 9:37 PM
 */
public class Authorization {
    /**
     * Заходит пользователем по логину и паролю
     *
     * @param email
     * @param pass
     * @throws StoreException
     */
    public void login(String email, String pass) throws StoreException {
        try {
            ResultSet rs = selectUser(email, pass);
            int count = 0;
            String name = "", role = "";
            while (rs.next()) {
                name = rs.getString("name");
                role = rs.getString("role");
                count++;
            }

            if (count == 0) {
                throw new StoreException("User not exist with this email and password");
            }

            // Add user info to session
            Session.setUserInfo(new User(email, name, role, pass));
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Регистрирует пользователя
     *
     * @param email
     * @param pass
     * @param name
     * @throws StoreException
     */
    public void signup(String email, String pass, String name) throws StoreException {
        try {
            if (pass.length() < 6) {
                throw new StoreException("Pass length must be over or equals 6 symbols");
            }

            if (!isValidEmailAddress(email)) {
                throw new StoreException("Email «" + email + "» not valid");
            }

            ResultSet rs = selectUser(email, pass);
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count != 0) {
                throw new StoreException("User with email «" + email + "» already exist");
            }

            PreparedStatement pst = Database.con().prepareStatement("INSERT INTO users(email, name, pass) VALUES(?, ?, ?);");
            pst.setString(1, email);
            pst.setString(2, name);
            pst.setString(3, pass);
            pst.executeUpdate();

            login(email, pass);
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Выходит из пользователя
     *
     * @throws StoreException
     */
    public void logout() throws StoreException {
        Session.setUserInfo(null);
    }

    /**
     * Выбирает пользователя из базы
     *
     * @param email
     * @param pass
     * @return
     * @throws SQLException
     */
    private ResultSet selectUser(String email, String pass) throws SQLException {
        PreparedStatement pst = Database.con().prepareStatement("SELECT email, name, role, pass FROM users WHERE email = ? AND pass = ?");
        pst.setString(1, email);
        pst.setString(2, pass);

        return pst.executeQuery();
    }

    /**
     * Проверяет валидность почты
     *
     * @param email
     * @return boolean
     */
    private static boolean isValidEmailAddress(String email) {
        // Set the email pattern string
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

        // Match the given string with the pattern
        Matcher m = p.matcher(email);

        return m.matches();
    }
}
