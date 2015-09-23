import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Представляет объект Корзина
 *
 * @since 12/10/12, 8:27 PM
 */
public class Cart {

    /**
     * Добавляет товар в корзину пользователя
     *
     * @param productID
     * @param userEmail
     * @throws StoreException
     */
    public void add(int productID, String userEmail) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("INSERT INTO cart(user_email, product_id, buy_data) VALUES(?, ?, NOW());");
            pst.setString(1, userEmail.trim().toLowerCase());
            pst.setInt(2, productID);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Показывает все товары в корзине
     *
     * @return
     * @throws StoreException
     */
    public ResultSet show() throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM cart WHERE user_email = ?");
            pst.setString(1, Session.getUserInfo().getEmail());

            return pst.executeQuery();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * "Покупает" все товары из корзины
     *
     * @throws StoreException
     */
    public void checkout() throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("DELETE FROM cart WHERE user_email = ?");
            pst.setString(1, Session.getUserInfo().getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }
}
