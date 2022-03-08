package lesson6.homework;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WeatherApp {

    private static final String PROPERTIES = "./src/main/java/lesson6/homework/data/data.properties";

    private static final Properties properties = new Properties();

    /**
     * Загружаем конфигурационный файл
     * @throws IOException если файла нет
     */
    private static void loadProperties () throws IOException {
        try (FileInputStream configFile = new FileInputStream(PROPERTIES)) {
            properties.load(configFile);
        }
    }

    public static void main(String[] args) throws IOException {
        // загружаем конфигурационные данные
        loadProperties();

        // создаем клиент
        OkHttpClient client = new OkHttpClient();

        // создаем URL
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(properties.getProperty("BASE_HOST"))
                .addPathSegment(properties.getProperty("API_VERSION"))
                .addPathSegment(properties.getProperty("FORECAST"))
                .addQueryParameter("lat", properties.getProperty("LAT"))
                .addQueryParameter("lon", properties.getProperty("LON"))
                .addQueryParameter("lang", "ru_RU")
                .addQueryParameter("limit", "5")
                .addQueryParameter("hours", "false")
                .addQueryParameter("extra", "false")
                .build();

        // формируем запрос, указываем заголовок
        Request request = new Request.Builder()
                .addHeader("X-Yandex-API-Key", properties.getProperty("X-Yandex-API-Key"))
                .url(url)
                .build();

        // получаем json
        String response = client.newCall(request).execute().body().string();

        System.out.println("JSON:");
        System.out.println(response);

    }

}
