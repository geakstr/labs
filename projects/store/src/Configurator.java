import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Хранит конфиги всего приложения
 *
 * @since 12/5/12, 1:41 PM
 */
public class Configurator {
    /**
     * Все конфиги приложения
     */
    private static Configurator configs = null;


    /**
     * База данных
     */
    public class DB {
        public String type;
        public String host;
        public String dbName;
        public String user;
        public String pass;
    }

    public DB db;

    /**
     * "Защитник", для разделения прав
     */
    public class Guard {
        public String add;
        public String remove;
        public String edit;
        public String cart;
    }

    public Guard guard;

    /**
     * Берет конфиги из файла configs.json
     *
     * @throws IOException
     */
    public static void setConfigs() throws IOException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("configs.json"));
        configs = gson.fromJson(br, Configurator.class);
        Configurator.configs = configs;
    }

    /**
     * Получает конфиги
     *
     * @return
     */
    public static Configurator get() {
        return configs;
    }
}
