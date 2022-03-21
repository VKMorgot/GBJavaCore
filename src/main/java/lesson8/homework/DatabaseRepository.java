package lesson8.homework;

import lesson8.homework.weather_data.Weather;

public interface DatabaseRepository {
    /**
     * Запись данных о погоде в базу данных.
     * Сохраняются данные не фактические, а данные прогнозов на день,
     * поэтому данные в базе и данные текущего прогноза могут не совпадать.
     * Так же не совпадают данные по текущему прогнозу, и прогнозу на 5 дней
     *
     * @param weather класс погоды
     */
    void saveWeatherData(Weather weather);
}
