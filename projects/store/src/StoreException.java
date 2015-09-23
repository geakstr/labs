/**
 * Представляет собственный эксепшн
 * @since 12/3/12, 9:54 PM
 */
public class StoreException extends Exception {
    private String message;

    public StoreException(String a) {
        this.message = a;
    }

    public String toString() {
        return this.message;
    }
}
