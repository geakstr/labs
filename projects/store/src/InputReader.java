import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Предоставляет ввод в консоль
 *
 * @since 12/4/12, 8:25 PM
 */
public class InputReader {
    BufferedReader in;
    StringTokenizer st;

    public InputReader() throws IOException {
        Locale.setDefault(Locale.US);
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Получаем строку
     *
     * @return
     * @throws IOException
     */
    String ns() throws IOException {
        return in.readLine();
    }

    /**
     * Получаем int
     *
     * @return
     * @throws IOException
     */
    int ni() throws IOException {
        return Integer.valueOf(ns());
    }

    /**
     * Получаем long
     *
     * @return
     * @throws IOException
     */
    long nl() throws IOException {
        return Long.valueOf(ns());
    }

    /**
     * Получаем double
     *
     * @return
     * @throws IOException
     */
    double nd() throws IOException {
        return Double.valueOf(ns());
    }

    public static void main(String[] args) throws IOException {
        new InputReader();
    }
}
