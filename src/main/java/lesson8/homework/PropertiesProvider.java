package lesson8.homework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для получения конфигурационных данных
 */
public class PropertiesProvider {

    private final Properties properties;

    /**
     * Загружаем конфигурационный файл
     *
     * @throws IOException если файла нет
     */
    private void loadProperties(String fileName) throws IOException {
        try (FileInputStream configFile = new FileInputStream(fileName)) {
            properties.load(configFile);
        }
    }

    public PropertiesProvider(String fileName) throws IOException {
        properties = new Properties();
        loadProperties(fileName);
    }

    public Properties getProperties() {
        return properties;
    }
}
