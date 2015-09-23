import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Менеджер товаров
 *
 * @since 12/5/12, 3:36 PM
 */
public class Products extends Items {

    /**
     * Получает товар из базы по названию
     *
     * @param productTitle
     * @return
     * @throws StoreException
     */
    public ResultSet get(String productTitle) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM products WHERE title like ?");
            pst.setString(1, "%" + productTitle.trim().toLowerCase() + "%");

            return pst.executeQuery();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Получает товар по ID
     *
     * @param productID
     * @return
     * @throws StoreException
     */
    public ResultSet getById(int productID) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM products WHERE id = ?");
            pst.setInt(1, productID);

            return pst.executeQuery();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Получает заголовок по ID
     *
     * @param productID
     * @return
     * @throws StoreException
     */
    public static String getTitleById(int productID) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM products WHERE id = ?");
            pst.setInt(1, productID);
            ResultSet rs = pst.executeQuery();
            return rs.getString("title");
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Получает категорию, к которой относится товар
     *
     * @param productID
     * @return
     * @throws StoreException
     */
    public String getCategoryOfProductByProductId(int productID) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT category_id FROM products_categories_rel WHERE product_id = ?");
            pst.setInt(1, productID);

            ResultSet rs = pst.executeQuery();

            int categoryId = -1;
            while (rs.next()) {
                categoryId = rs.getInt("category_id");
            }

            pst = Database.con().prepareStatement("SELECT title FROM categories WHERE id = ?");
            pst.setInt(1, categoryId);

            rs = pst.executeQuery();
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
     * Показывает все товары в категории
     *
     * @param categoryTitle
     * @return
     * @throws StoreException
     */
    public ResultSet getAllFromCategory(String categoryTitle) throws StoreException {
        try {
            int categoryId = Categories.getIdOfCategoryByTitle(categoryTitle);
            PreparedStatement pst = Database.con().prepareStatement("SELECT *\n" +
                    "            FROM products AS p\n" +
                    "            LEFT JOIN products_categories_rel AS r\n" +
                    "            ON p.id = r.product_id\n" +
                    "            WHERE r.category_id = ? ORDER BY price;");
            pst.setInt(1, categoryId);

            return pst.executeQuery();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Добавляет товар
     *
     * @param productTitle
     * @param categoryTitle
     * @param productPrice
     * @throws StoreException
     */
    public void add(String productTitle, String categoryTitle, double productPrice) throws StoreException {
        try {
            // Проверяем, есть ли такой продукт уже
            if (checkItemExist(productTitle, "products")) {
                throw new StoreException("Product «" + productTitle + "» already exist");
            }
            // Проверяем наличие категории
            if (!checkItemExist(categoryTitle, "categories")) {
                throw new StoreException("Category «" + categoryTitle + "» not found");
            }

            // Засовываем продукт
            PreparedStatement pst = Database.con().prepareStatement("INSERT INTO products(title, price) VALUES(?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, productTitle);
            pst.setDouble(2, productPrice);
            pst.executeUpdate();

            // Получаем его ID
            ResultSet productIdSet = pst.getGeneratedKeys();
            int productId = 0;
            if (productIdSet.next())
                productId = productIdSet.getInt(1);

            int categoryId = Categories.getIdOfCategoryByTitle(categoryTitle);

            // Задаем связь продукт-категория
            pst = Database.con().prepareStatement("INSERT INTO products_categories_rel(product_id, category_id) VALUES(?, ?);");
            pst.setInt(1, productId);
            pst.setInt(2, categoryId);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Удаляет товар
     *
     * @param productID
     * @throws StoreException
     */
    public void remove(int productID) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("DELETE FROM products WHERE id = ?;");
            pst.setInt(1, productID);
            pst.executeUpdate();

            pst = Database.con().prepareStatement("DELETE FROM products_categories_rel WHERE product_id = ?;");
            pst.setInt(1, productID);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Редактирует товар
     *
     * @param productId
     * @param newProductTitle
     * @param newProductPrice
     * @throws StoreException
     */
    public void edit(int productId, String newProductTitle, double newProductPrice) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("UPDATE products SET title = ?, price = ? WHERE id = ?;");
            pst.setString(1, newProductTitle);
            pst.setDouble(2, newProductPrice);
            pst.setInt(3, productId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }
}
