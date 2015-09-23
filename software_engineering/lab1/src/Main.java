import java.io.*;

public class Main {
    public static void main(String[] args) {
        StringBuilder table_1 = new StringBuilder(), table_2 = new StringBuilder();

        table_2.append(" период    цена       деньги         акции     капитал    ")
                .append("\n-------------------------------------------------------\n");

        String action;
        int up = -1, down = -1, period = 1;
        double cost, prev_cost = 0, money = 10000, stocks = 0;

        try (BufferedReader in = new BufferedReader(new FileReader("st15.txt"))) {
            while (in.ready()) {
                cost = Double.parseDouble(in.readLine());

                action = "";
                if (cost < prev_cost) {
                    down++;
                    if (up > 2) {
                        action = "продавать";
                        if (stocks > 0) {
                            money = stocks * cost;
                            stocks = 0;
                        }
                    }
                    up = 0;
                } else if (cost > prev_cost) {
                    up++;
                    if (down > 2) {
                        action = "покупать";
                        if (money > 0) {
                            stocks = money / cost;
                            money = 0;
                        }
                    }
                    down = 0;
                } else {
                    up = down = 0;
                }

                table_1.append(String.format("%6d   ", period));
                long rounded_cost = Math.round(cost);
                while (rounded_cost-- > 0) {
                    table_1.append('*');
                }
                table_1.append(String.format("%7.3f %s%n", cost, action));

                table_2.append(String.format("%6d   %7.3f %12.2f %12.2f %12.2f%n",
                        period,
                        cost,
                        money,
                        stocks,
                        money + stocks * cost));

                prev_cost = cost;
                period++;
            }
        } catch (IOException ignore) {

        } finally {
            System.out.println(table_1 + "\n" + table_2);
        }
    }
}
