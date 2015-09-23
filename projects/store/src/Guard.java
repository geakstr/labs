/**
 * Разделяет права между пользователями
 *
 * @since 12/9/12, 8:13 PM
 */
public class Guard {
    /**
     * Проверяет соответствие роли введенной команде
     *
     * @param command
     * @param action
     * @return
     */
    public boolean checkAllowable(String command, String action) {
        User user = Session.getUserInfo();
        if (user == null && !command.equals("show")) {
            return false;
        }

        String guardCommand = "";
        if (command.equals("add")) {
            guardCommand = Configurator.get().guard.add;
        } else if (command.equals("remove")) {
            guardCommand = Configurator.get().guard.remove;
        } else if (command.equals("edit")) {
            guardCommand = Configurator.get().guard.edit;
        } else if(command.equals("cart")) {
            guardCommand = Configurator.get().guard.cart;
        } else {
            return true;
        }

        return user.getRole().equals(guardCommand);
    }
}
