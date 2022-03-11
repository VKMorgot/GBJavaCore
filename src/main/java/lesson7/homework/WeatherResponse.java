package lesson7.homework;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class WeatherResponse {

    private final String CONDITIONS = "./src/main/resources/lesson7.homework/conditions.properties";
    private final PropertiesProvider conditions = new PropertiesProvider(CONDITIONS);

    public WeatherResponse() throws IOException {
    }

    /**
     * Печать на экран информации о погоде
     *
     * @param jsonStr полученная json строка
     * @param period  период, за который показываем погоду
     * @throws JsonProcessingException в случае проблемы с json
     */
    public void printWeather(String jsonStr, Period period) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = objectMapper.readValue(jsonStr, Weather.class);

        switch (period) {
            case NOW:
                printWeatherFact(weather);
                break;
            case FIVE_DAYS:
                printWeatherFiveDays(weather);
                break;
        }
    }

    /**
     * Вывод в консоль информации о текущей погоде
     *
     * @param weather объект "Погода", полученный из json
     */
    private void printWeatherFact(Weather weather) {
        String cityName = weather.getGetObject().getLocalityObject().getName();
        String weatherText = conditions.getProperties().getProperty(weather.getFactObject().getCondition());
        float temperature = weather.getFactObject().getTemp();
        float feelsLike = weather.getFactObject().getFeelsLike();

        System.out.printf("В городе %s сегодня %s, температура: %1.0f °С. Ощущается как %1.0f °С\n",
                cityName, weatherText, temperature, feelsLike);

    }

    /**
     * Вывод в консоль информацию о погоде на 5 дней
     *
     * @param weather объект "Погода", полученный из json
     */
    private void printWeatherFiveDays(Weather weather) {
        String cityName = weather.getGetObject().getLocalityObject().getName();
        System.out.printf("Прогноз погоды в городе %s на %s дней\n", cityName, Period.FIVE_DAYS.getDays());
        for (Forecast forecast : weather.getForecasts()) {
            String date = forecast.getDate();
            String weatherText = conditions.getProperties().getProperty(forecast.getPartsObject().getDayShortObject().getCondition());
            float temperature = forecast.getPartsObject().getDayShortObject().getTemp();
            float feelsLike = forecast.getPartsObject().getDayShortObject().getFeelsLike();

            System.out.printf("%s будет %s, температура: %1.0f °С. Ощущается как %1.0f °С\n",
                    date, weatherText, temperature, feelsLike);
        }
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Weather {

    @JsonProperty(value = "now_dt")
    private String nowDt;
    @JsonProperty(value = "geo_object")
    private Geo getObject;
    @JsonProperty(value = "fact")
    Fact factObject;
    ArrayList<Forecast> forecasts = new ArrayList<>();

    public Geo getGetObject() {
        return getObject;
    }

    public String getNowDt() {
        return nowDt;
    }

    public Fact getFactObject() {
        return factObject;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Forecast {
    private String date;
    @JsonProperty(value = "parts")
    private Parts partsObject;

    public String getDate() {
        return date;
    }

    public Parts getPartsObject() {
        return partsObject;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Fact {
    private float temp;
    @JsonProperty(value = "feels_like")
    private float feelsLike;
    private String condition;

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public String getCondition() {
        return condition;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Parts {
    @JsonProperty(value = "day_short")
    private DayShort dayShortObject;

    public DayShort getDayShortObject() {
        return dayShortObject;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class DayShort {
    private float temp;
    private String condition;
    @JsonProperty(value = "feels_like")
    private float feelsLike;

    public float getFeelsLike() {
        return feelsLike;
    }

    public float getTemp() {
        return temp;
    }

    public String getCondition() {
        return condition;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Geo {
    @JsonProperty(value = "locality")
    private Locality localityObject;

    public Locality getLocalityObject() {
        return localityObject;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Locality {
    private String name;

    public String getName() {
        return name;
    }
}