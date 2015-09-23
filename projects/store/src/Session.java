/**
 * Храни различную информацию о сессии пользователя
 *
 * @since 12/3/12, 9:40 PM
 */
public class Session {
    /**
     * Информация о пользователе
     *
     * @see User
     */
    private static User userInfo = null;

    /**
     * Получиет информацию о пользователе
     *
     * @return User
     */
    public static User getUserInfo() {
        return userInfo;
    }

    /**
     * Устанавливает информацию пользователя
     *
     * @param userInfo
     */
    public static void setUserInfo(User userInfo) {
        Session.userInfo = userInfo;
    }
}
