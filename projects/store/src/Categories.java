import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Менеджер категорий
 *
 * @since 12/5/12, 3:14 PM
 */
public class Categories extends Items {
    /**
     * Добавляет категорию
     *
     * @param categoryTitle
     * @throws StoreException
     */
    public void add(String categoryTitle) throws StoreException {
        try {
            if (checkItemExist(categoryTitle, "categories")) {
                throw new StoreException("Category «" + categoryTitle + "» already exist");
            }

            PreparedStatement pst = Database.con().prepareStatement("INSERT INTO categories(title) VALUES(?);");
            pst.setString(1, categoryTitle.trim().toLowerCase());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Получает ID категории по названию
     *
     * @param categoryTitle
     * @return
     * @throws StoreException
     */
    public static int getIdOfCategoryByTitle(String categoryTitle) throws StoreException {
        PreparedStatement pst = null;
        try {
            pst = Database.con().prepareStatement("SELECT id FROM categories WHERE title = ?;");
            pst.setString(1, categoryTitle.trim().toLowerCase());

            ResultSet rs = pst.executeQuery();

            int count = 0;
            int categoryId = 0;
            while (rs.next()) {
                count++;
                categoryId = rs.getInt("id");
            }
            if (count == 0) {
                return -1;
            }

            return categoryId;
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Получает название категории по ID
     *
     * @param categoryId
     * @return
     * @throws StoreException
     */
    public static String getTitleById(int categoryId) throws StoreException {
        PreparedStatement pst = null;
        try {
            pst = Database.con().prepareStatement("SELECT title FROM categories WHERE id = ?;");
            pst.setInt(1, categoryId);

            ResultSet rs = pst.executeQuery();
            String categoryTitle = "";
            while (rs.next()) {
                categoryTitle = rs.getString("title");
            }

            return categoryTitle;
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Редактирует категорию
     *
     * @param categoryTitle
     * @param newCategoryTitle
     * @throws StoreException
     */
    public void edit(String categoryTitle, String newCategoryTitle) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("UPDATE categories SET title = ? WHERE title = ?;");
            pst.setString(1, newCategoryTitle);
            pst.setString(2, categoryTitle);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }
}
