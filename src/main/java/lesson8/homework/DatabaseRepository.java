package lesson8.homework;

import lesson8.homework.weather_data.Weather;

import java.sql.SQLException;
import java.util.ArrayList;

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

    /**
     * Читаем все данные из базы данных
     *
     * @return список всех строк из базы
     */
    ArrayList<Weather> getAllSavedData() throws MyObjectSaveException;

    /**
     * Читаем данные из базы данных по заданным городу и дате
     *
     * @param cityName город для поиска погоды
     * @param date     дата для поиска погоды
     * @return найденные строки, если таких данных несколько
     */
    ArrayList<Weather> getCitySavedDataForDate(String cityName, String date) throws MyObjectSaveException;

    /**
     * Читаем данные из базы данных по заданной дате для всех городов
     *
     * @param date дата для поиска погоды
     * @return найденные строки, если таких данных несколько
     */
    ArrayList<Weather> getSavedDataForDate(String date) throws MyObjectSaveException;

}
