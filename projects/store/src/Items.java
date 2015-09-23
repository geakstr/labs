import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Базовый класс над продуктом и категорией
 *
 * @since 12/5/12, 4:02 PM
 */
public class Items {

    /**
     * Проверяет наличие объекта в базе
     *
     * @param itemTitle
     * @param tableName
     * @return
     * @throws StoreException
     */
    public boolean checkItemExist(String itemTitle, String tableName) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT title FROM " + tableName + " WHERE title = ? LIMIT 1");
            pst.setString(1, itemTitle);

            return Database.count(pst.executeQuery()) != 0;
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }
}
