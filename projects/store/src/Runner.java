import java.io.IOException;

/**
 * Запускает приложение
 *
 * @since 12/3/12, 6:57 PM
 */
public class Runner {
    public static void main(String[] args) throws IOException {
        Configurator.setConfigs();
        new UI().chooser();
    }
}
