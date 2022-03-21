package lesson8.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Класс для поиска координат города по его названию
 */
public class YandexCityProvider implements CityProvider {

    // работа с конфигом
    private final String PROPERTIES = "./src/main/resources/lesson8.homework/yandex-geo.properties";
    private final PropertiesProvider properties;

    // работа с json
    private final String JSON_CITY = "/response/GeoObjectCollection/featureMember/0/GeoObject/name";
    private final String JSON_COUNTRY = "/response/GeoObjectCollection/featureMember/0/GeoObject/description";
    private final String JSON_COORDINATES = "/response/GeoObjectCollection/featureMember/0/GeoObject/Point/pos";

    public YandexCityProvider() throws IOException {
        this.properties = new PropertiesProvider(PROPERTIES);
    }

    @Override
    public void getCoordinates(City city) throws IOException {
        // создаем клиент
        OkHttpClient client = new OkHttpClient();

        // создаем URL
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(properties.getProperties().getProperty("BASE_HOST"))
                .addPathSegment(properties.getProperties().getProperty("API_VERSION"))
                .addQueryParameter("geocode", city.getCityName())
                .addQueryParameter("apikey", ApplicationGlobalState.getInstance().getGEO_API_KEY())
                .addQueryParameter("format", "json")
                .addQueryParameter("lang", "ru_RU")
                .build();

        // формируем запрос, указываем заголовок
        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();


        // получаем json
        System.out.println("Поиск города: " + city.getCityName() + "\n");
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Невозможно получить информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();

        // если ответ не пустой и найден заданный город, устанавливаем значения координат
        ObjectMapper objectMapper = new ObjectMapper();
        if (objectMapper.readTree(jsonResponse).size() > 0 && objectMapper.readTree(jsonResponse).at(JSON_CITY).asText().equals(city.getCityName())) {
            city.setCityLocation(objectMapper.readTree(jsonResponse).at(JSON_COUNTRY).asText());
            city.setCityLongitude(objectMapper.readTree(jsonResponse).at(JSON_COORDINATES).asText().split(" ")[0]);
            city.setCityLatitude(objectMapper.readTree(jsonResponse).at(JSON_COORDINATES).asText().split(" ")[1]);
            System.out.println("Найден город: " + city.getCityName());
            System.out.println("Место расположения: " + city.getCityLocation());
        } else System.out.println("Город " + city.getCityName() + " не был найден");
    }
}
