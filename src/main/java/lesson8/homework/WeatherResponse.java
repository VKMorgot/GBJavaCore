package lesson8.homework;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson8.homework.weather_data.*;

import java.io.IOException;

public class WeatherResponse {

    private final String CONDITIONS = "./src/main/resources/lesson8.homework/conditions.properties";
    private final PropertiesProvider conditions = new PropertiesProvider(CONDITIONS);
    // полученная из json погода
    private final Weather weather;

    public WeatherResponse(String jsonStr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        weather = objectMapper.readValue(jsonStr, Weather.class);
    }

    /**
     * Печать на экран информации о погоде
     *
     * @param period период, за который показываем погоду
     */
    public void printWeather(Period period) {
        switch (period) {
            case NOW:
                printWeatherFact();
                break;
            case FIVE_DAYS:
                printWeatherFiveDays();
                break;
        }
    }

    /**
     * Вывод в консоль информации о текущей погоде
     */
    private void printWeatherFact() {
        String cityName = weather.getGetObject().getLocalityObject().getName();
        String weatherText = conditions.getProperties().getProperty(weather.getFactObject().getCondition());
        float temperature = weather.getFactObject().getTemp();
        float feelsLike = weather.getFactObject().getFeelsLike();

        System.out.printf("В городе %s сегодня %s, температура: %1.0f °С. Ощущается как %1.0f °С\n",
                cityName, weatherText, temperature, feelsLike);

    }

    /**
     * Вывод в консоль информацию о погоде на 5 дней
     */
    private void printWeatherFiveDays() {
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

    /**
     * Сохраняем погоду в базу данных
     */
    public void saveWeather() throws IOException {
        DatabaseRepository databaseRepository = new SQLiteImplementation();
        databaseRepository.saveWeatherData(weather);
    }

}

