import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Представляет отчеты
 *
 * @since 12/24/12, 6:25 PM
 */
public class Reports {
    /**
     * Отчет по продукту
     *
     * @param productId
     * @return
     * @throws StoreException
     */
    public String product(int productId) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM cart WHERE product_id = ?");
            pst.setInt(1, productId);

            ResultSet rs = pst.executeQuery();
            Products product = new Products();
            int count = 0;
            double totalCost = 0.0;
            double price = 0.0;
            String title = "";
            while (rs.next()) {
                count++;
                ResultSet productSet = product.getById(productId);
                while (productSet.next()) {
                    price = productSet.getDouble("price");
                    title = productSet.getString("title");
                }
                totalCost += price;
            }
            pst.close();
            rs.close();
            return "*** REPORT for product «" + title + "» ***\n" + "Amount of sales: " + count + "\n" + "Total cost: " + totalCost;
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Отчет по продукту, но с указанием временных рамок
     *
     * @param productId
     * @param from
     * @param to
     * @return
     * @throws StoreException
     */
    public String product(int productId, String from, String to) throws StoreException {
        try {
            Date fromDate, toDate;
            try {
                fromDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(from);
                toDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(to);
            } catch (ParseException e) {
                throw new StoreException(e.getMessage());
            }
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM cart WHERE product_id = ? AND buy_data >= ? AND buy_data <= ?");
            pst.setInt(1, productId);
            pst.setDate(2, new java.sql.Date(fromDate.getTime()));
            pst.setDate(3, new java.sql.Date(toDate.getTime()));

            ResultSet rs = pst.executeQuery();
            Products product = new Products();
            int count = 0;
            double totalCost = 0.0;
            double price = 0.0;
            String title = "";
            while (rs.next()) {
                count++;
                ResultSet productSet = product.getById(productId);
                while (productSet.next()) {
                    price = productSet.getDouble("price");
                    title = productSet.getString("title");
                }
                totalCost += price;
            }
            pst.close();
            rs.close();
            return "*** REPORT for product «" + title + "» ***\n" + "Amount of sales: " + count + "\n" + "Total cost: " + totalCost;
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Отчет по категории
     *
     * @param categoryTitle
     * @return
     * @throws StoreException
     */
    public String category(String categoryTitle) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM cart");

            ResultSet rs = pst.executeQuery();
            Products product = new Products();
            int count = 0;
            double totalCost = 0.0;
            double price = 0.0;
            String categoryGetTitle = "";
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                ResultSet productSet = product.getById(productId);
                PreparedStatement pstCategoryRel = Database.con().prepareStatement("SELECT * FROM products_categories_rel WHERE product_id = ?");
                pstCategoryRel.setInt(1, productId);
                ResultSet categorySet = pstCategoryRel.executeQuery();
                while (productSet.next()) {
                    price = productSet.getDouble("price");
                }
                while (categorySet.next()) {
                    categoryGetTitle = Categories.getTitleById(categorySet.getInt("category_id"));
                }
                if (categoryTitle.equals(categoryGetTitle)) {
                    count++;
                    totalCost += price;
                }

            }
            pst.close();
            rs.close();
            return "*** REPORT for category «" + categoryTitle + "» ***\n" + "Amount of sales: " + count + "\n" + "Total cost: " + totalCost;
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Отчет по категории, но с указанием временных рамок
     *
     * @param categoryTitle
     * @param from
     * @param to
     * @return
     * @throws StoreException
     */
    public String category(String categoryTitle, String from, String to) throws StoreException {
        try {

            Date fromDate, toDate;
            try {
                fromDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(from);
                toDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(to);
            } catch (ParseException e) {
                throw new StoreException(e.getMessage());
            }
            PreparedStatement pst = Database.con().prepareStatement("SELECT * FROM cart WHERE buy_data >= ? AND buy_data <= ?");
            pst.setDate(1, new java.sql.Date(fromDate.getTime()));
            pst.setDate(2, new java.sql.Date(toDate.getTime()));

            ResultSet rs = pst.executeQuery();
            Products product = new Products();
            int count = 0;
            double totalCost = 0.0;
            double price = 0.0;
            String categoryGetTitle = "";
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                ResultSet productSet = product.getById(productId);
                PreparedStatement pstCategoryRel = Database.con().prepareStatement("SELECT * FROM products_categories_rel WHERE product_id = ?");
                pstCategoryRel.setInt(1, productId);
                ResultSet categorySet = pstCategoryRel.executeQuery();
                while (productSet.next()) {
                    price = productSet.getDouble("price");
                }
                while (categorySet.next()) {
                    categoryGetTitle = Categories.getTitleById(categorySet.getInt("category_id"));
                }
                if (categoryTitle.equals(categoryGetTitle)) {
                    count++;
                    totalCost += price;
                }

            }
            pst.close();
            rs.close();
            return "*** REPORT for category «" + categoryTitle + "» ***\n" + "Amount of sales: " + count + "\n" + "Total cost: " + totalCost;
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }

    /**
     * Самые хорошо-продаваемые товары
     *
     * @param limit
     * @return
     * @throws StoreException
     */
    public String topSales(int limit) throws StoreException {
        try {
            PreparedStatement pst = Database.con().prepareStatement("SELECT *, COUNT(*) FROM cart GROUP BY product_id ORDER BY COUNT(*) DESC LIMIT ?");
            pst.setInt(1, limit);
            ResultSet rs = pst.executeQuery();

            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println(count + ". -----------------------");
                System.out.println(this.product(rs.getInt("product_id")));
                System.out.println();
            }

            return "";
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }
}
