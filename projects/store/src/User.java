/**
 * Представляет объект Пользователь
 *
 * @since 12/3/12, 9:25 PM
 */
public class User {
    /**
     * Электронная почта
     */
    private String email = null;
    /**
     * Имя пользователя
     */
    private String name = null;

    /**
     * Роль пользователя в системе
     */
    private String role = null;

    /**
     * Пароль пользователя
     */
    private String pass = null;

    /**
     * Конструктор. Устанавливает значения для полей Пользователя
     *
     * @param email
     * @param name
     * @param role
     */
    public User(String email, String name, String role, String pass) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.pass = pass;
    }

    /**
     * Получает почту пользователя
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает почту пользователя
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Получает имя пользователя
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя пользователя
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получает роль пользователя
     *
     * @return String
     */
    public String getRole() {
        return role;
    }

    /**
     * Устанавливает роль пользователя
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }


    /**
     * Получает пароль пользователя
     *
     * @return String
     */
    public String getPass() {
        return pass;
    }

    /**
     * Устанавливает пароль пользователя
     *
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Распечатывает данные пользователя
     *
     * @return String
     */
    public String toString() {
        String ret = "";

        ret += "Email: " + email + "\n";
        ret += "Name: " + name + "\n";

        return ret;
    }
}
