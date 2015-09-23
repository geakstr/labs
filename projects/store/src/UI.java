import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Предоставляет пользовательский интерфейс
 *
 * @since 12/3/12, 10:03 PM
 */
public class UI {
    private InputReader in;
    private PrintWriter out;
    private Menu menu;
    private Guard guard;


    /**
     * Допустимые команды
     */
    enum commands {
        show,
        login,
        logout,
        signup,
        add,
        edit,
        cart,
        remove,
        report
    }

    /**
     * Допустимые дейтствия
     */
    enum actions {
        profile,
        category,
        product,
        add,
        buy,
        show,
        checkout,
        top,
        productrange,
        categoryrange
    }

    public UI() throws IOException {
        in = new InputReader();
        out = new PrintWriter(System.out, true);
        menu = new Menu(90);
        guard = new Guard();
    }

    /**
     * Основной интерфейс
     *
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public void chooser() throws IOException, IllegalArgumentException {
        String[] input = new String[3];
        String inputCommand = "", inputAction = "", inputParam = "";
        Authorization auth = new Authorization();

        outer:
        while (true) {
            try {
                System.out.println(menu.line());

                menu.printMenu();

                System.out.print("> ");

                input = in.ns().split(" ");

                System.out.println();

                inputCommand = input[0].trim().toLowerCase();

                boolean checkAllowable = true;
                if (inputCommand.equals("exit")) {
                    System.out.println("Good bye!");
                    break outer;
                }

                if (input.length > 1) {
                    inputAction = input[1].trim().toLowerCase();
                    if (!guard.checkAllowable(inputCommand, inputAction)) {
                        throw new StoreException("Access denied! You must be login");
                    }
                }

                switch (commands.valueOf(inputCommand)) {
                    case show: {
                        switch (actions.valueOf(inputAction)) {
                            case profile: {
                                User userInfo = Session.getUserInfo();
                                if (userInfo == null) {
                                    System.out.println("You need login (type \"login\" or \"signup\")\n");
                                    continue outer;
                                }
                                System.out.println("*** YOUR PROFILE ***");
                                System.out.println(userInfo.toString());
                                break;
                            }
                            case product: {
                                showProduct();
                                break;
                            }
                            case category: {
                                Products product = new Products();
                                System.out.print("Title of category: ");
                                String categoryTitle = in.ns();
                                ResultSet rs = product.getAllFromCategory(categoryTitle);
                                System.out.println("\n******* Category «" + categoryTitle + "» ******");
                                try {
                                    int count = 0;
                                    while (rs.next()) {
                                        count++;
                                        System.out.print(count + ". ");
                                        System.out.println("ID: " + rs.getInt("id"));
                                        System.out.println("   Title: " + rs.getString("title"));
                                        System.out.println("   Price: " + rs.getString("price"));
                                        System.out.println();
                                    }
                                    if (count == 0) {
                                        System.out.println("Category empty");
                                    }
                                } catch (SQLException e) {
                                    throw new StoreException(e.getMessage());
                                }
                                break;
                            }
                            default:
                                break;
                        }
                        break;
                    }
                    case add: {
                        switch (actions.valueOf(inputAction)) {
                            case category: {
                                Categories category = new Categories();
                                System.out.println("******* Adding category ******");
                                System.out.print("Title: ");
                                String title = in.ns();
                                category.add(title);
                                break;
                            }
                            case product: {
                                Products product = new Products();
                                System.out.println("******* Adding product ******");
                                System.out.print("Title: ");
                                String productTitle = in.ns();
                                System.out.print("Category: ");
                                String categoryTitle = in.ns();
                                System.out.print("Price: ");
                                double productPrice = in.nd();
                                product.add(productTitle, categoryTitle, productPrice);
                                break;
                            }
                            default:
                                break;
                        }
                        break;
                    }
                    case edit: {
                        switch (actions.valueOf(inputAction)) {
                            case product: {
                                Products product = new Products();
                                System.out.println("******* Editing product ******");
                                showProduct();
                                System.out.print("Edit product with ID: ");
                                int productID = in.ni();
                                System.out.print("New title: ");
                                String newProductTitle = in.ns();
                                System.out.print("New price: ");
                                double newProductPrice = in.nd();
                                product.edit(productID, newProductTitle, newProductPrice);
                                break;
                            }
                            case category: {
                                Categories category = new Categories();
                                System.out.println("******* Editing category ******");

                                System.out.print("Edit category with title: ");
                                String categoryTitle = in.ns();
                                System.out.print("New title: ");
                                String newCategoryTitle = in.ns();
                                category.edit(categoryTitle, newCategoryTitle);
                                break;
                            }
                        }
                        break;
                    }
                    case remove: {
                        Products product = new Products();
                        System.out.println("******* Remove product ******");
                        showProduct();
                        System.out.print("Remove product with ID: ");
                        int productID = in.ni();
                        product.remove(productID);
                        break;
                    }
                    case cart: {
                        switch (actions.valueOf(inputAction)) {
                            case buy: {
                                Cart cart = new Cart();
                                System.out.println("******* BUY PRODUCT ******");
                                showProduct();
                                System.out.print("ID of product: ");
                                int productID = in.ni();
                                String userEmail = Session.getUserInfo().getEmail();
                                cart.add(productID, userEmail);
                                break;
                            }
                            case show: {
                                Cart cart = new Cart();
                                Products product = new Products();
                                try {
                                    ResultSet rsCart = cart.show();

                                    int cnt = 0;
                                    System.out.println("******* Your cart *******");
                                    System.out.println();
                                    while (rsCart.next()) {
                                        int productId = rsCart.getInt("product_id");
                                        ResultSet rsProduct = product.getById(productId);
                                        cnt++;
                                        String buyDate = rsCart.getString("buy_data");
                                        while (rsProduct.next()) {
                                            System.out.println(cnt + ". Title: " + rsProduct.getString("title"));
                                            System.out.println("   Price: " + rsProduct.getString("price"));
                                            String categoryTitle = product.getCategoryOfProductByProductId(productId);
                                            System.out.println("   Category: " + categoryTitle);
                                        }
                                        System.out.println("   Date: " + buyDate);
                                        System.out.println();
                                    }
                                    if (cnt == 0) {
                                        System.out.println("Cart is empty :-(. Buy something!");
                                    }
                                } catch (SQLException e) {
                                    throw new StoreException(e.getMessage());
                                }
                                break;
                            }
                            case checkout: {
                                System.out.println("******* CHECKOUT CART ******");
                                System.out.println("Are you sure? (yes/no)");
                                String answer = in.ns().trim().toLowerCase();
                                if (answer.equals("yes")) {
                                    Cart cart = new Cart();
                                    cart.checkout();
                                } else {
                                    System.out.println("Ok. Cart is not touched.");
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case report: {
                        switch (actions.valueOf(inputAction)) {
                            case product: {
                                System.out.println("******* SHOW REPORT ******");
                                showProduct();
                                Reports report = new Reports();
                                System.out.print("ID of product: ");
                                int productID = in.ni();
                                System.out.println();
                                String rep = report.product(productID);
                                System.out.println(rep);
                                break;
                            }
                            case category: {
                                System.out.println("******* SHOW REPORT ******");
                                Reports report = new Reports();
                                System.out.print("Title of category: ");
                                String categoryTitle = in.ns();
                                System.out.println();
                                String rep = report.category(categoryTitle);
                                System.out.println(rep);
                                break;
                            }
                            case top: {
                                System.out.println("******* SHOW REPORT ******");
                                Reports report = new Reports();
                                System.out.print("Limit: ");
                                int limit = in.ni();
                                System.out.println("\n       +++ TOP " + limit + " +++");
                                report.topSales(limit);
                                break;
                            }
                            case productrange: {
                                System.out.println("******* SHOW REPORT ******");
                                showProduct();
                                Reports report = new Reports();
                                System.out.print("ID of product: ");
                                int productID = in.ni();
                                System.out.print("Date (from): ");
                                String from = in.ns();
                                System.out.print("Date (to): ");
                                String to = in.ns();
                                System.out.println();
                                System.out.println(report.product(productID, from, to));
                                break;
                            }
                            case categoryrange: {
                                System.out.println("******* SHOW REPORT ******");
                                Reports report = new Reports();
                                System.out.print("Title of category: ");
                                String categoryTitle = in.ns();

                                System.out.print("Date (from): ");
                                String from = in.ns();
                                System.out.print("Date (to): ");
                                String to = in.ns();
                                System.out.println();
                                String rep = report.category(categoryTitle, from, to);
                                System.out.println(rep);
                                break;
                            }
                        }
                        break;
                    }
                    case login: {
                        System.out.print("Email: ");
                        String email = in.ns();
                        System.out.print("Pass: ");
                        String pass = in.ns();
                        auth.login(email, pass);
                        break;
                    }
                    case signup: {
                        System.out.print("Email: ");
                        String email = in.ns();
                        System.out.print("Pass: ");
                        String pass = in.ns();
                        System.out.print("Name: ");
                        String name = in.ns();
                        auth.signup(email, pass, name);
                        break;
                    }
                    case logout: {
                        auth.logout();
                        break;
                    }
                    default:
                        break;
                }

                System.out.println();
            } catch (StoreException e) {
                menu.printException(e.toString());
            } catch (IllegalArgumentException e) {
                menu.printException("Command not allow");
            }
        }

    }

    /**
     * Показывает информацию о продукте (хелпер)
     *
     * @throws StoreException
     * @throws IOException
     */
    private void showProduct() throws StoreException, IOException {
        Products product = new Products();
        System.out.print("Title of product: ");
        String productTitle = in.ns();
        ResultSet rs = product.get(productTitle);
        System.out.println("\n******* Products like «" + productTitle + "» ******");
        try {
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.print(count + ". ");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("   Title: " + rs.getString("title"));
                System.out.println("   Price: " + rs.getString("price"));
                System.out.println();
            }
            if (count == 0) {
                throw new StoreException("Product not exist");
            }
        } catch (SQLException e) {
            throw new StoreException(e.getMessage());
        }
    }


    private class Menu {
        private int width = 100;

        public Menu(int width) {
            this.width = width;
        }

        /**
         * Печатает меню пользователя
         */
        public void printMenu() {
            System.out.println("Allow commands: ");
            System.out.println("  1. login, signup, logout");
            if (Session.getUserInfo() == null) {
                System.out.println("  2. show [category, product]");
            } else {
                String userRole = Session.getUserInfo().getRole();
                if (userRole.equals("admin")) {
                    System.out.println("  2. show [profile, category, product]");
                    System.out.println("  3. add [category, product]");
                    System.out.println("  4. edit");
                    System.out.println("  5. remove");
                    System.out.println("  6. report [product, category, productrange, categoryrange]");
                } else {
                    System.out.println("  2. show [profile, category]");
                    System.out.println("  3. cart [buy, show, checkout]");
                }
            }
        }

        /**
         * Печатает линию
         *
         * @return
         */
        public String line() {
            String ret = "";
            for (int i = 0; i < width; i++) {
                ret += "-";
            }
            return ret;
        }

        /**
         * Выводит сообщение об ошибке
         *
         * @param e
         */
        public void printException(String e) {
            System.out.println("\n!!!!!!!!!!!!!!!");
            System.out.println(e);
            System.out.println("!!!!!!!!!!!!!!!\n");
        }
    }

}
