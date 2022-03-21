package lesson8.homework;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Класс для поиска погоды на Яндексе
 */
public class YandexWeatherProvider implements WeatherProvider {

    private final String PROPERTIES = "./src/main/resources/lesson8.homework/yandex-werther.properties";
    private final PropertiesProvider properties;

    public YandexWeatherProvider() throws IOException {
        properties = new PropertiesProvider(PROPERTIES);
    }

    @Override
    public void getWeather(City city, Period period) throws IOException {
        // создаем клиент
        OkHttpClient client = new OkHttpClient();

        // создаем URL
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(properties.getProperties().getProperty("BASE_HOST"))
                .addPathSegment(properties.getProperties().getProperty("API_VERSION"))
                .addPathSegment(properties.getProperties().getProperty("FORECAST"))
                .addQueryParameter("lat", city.getCityLatitude())
                .addQueryParameter("lon", city.getCityLongitude())
                .addQueryParameter("lang", "ru_RU")
                .addQueryParameter("limit", period.getDays())
                .addQueryParameter("hours", "false")
                .addQueryParameter("extra", "false")
                .build();

        // формируем запрос, указываем заголовок
        Request request = new Request.Builder()
                .addHeader("X-Yandex-API-Key", ApplicationGlobalState.getInstance().getX_YANDEX_API_KEY())
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        // получаем json
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Невозможно получить информацию о погоде. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();

        WeatherResponse weatherResponse = new WeatherResponse(jsonResponse);
        weatherResponse.printWeather(period);
        weatherResponse.saveWeather();
    }

}
