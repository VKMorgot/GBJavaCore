package lesson8.homework;

import java.io.IOException;

public interface WeatherProvider {
    /**
     * Поиск погоды в указанном городе.
     * Выдаем текущие результаты, и результаты за ближайшие 5 дней
     *
     * @param city   класс Город
     * @param period период, за который выдаем результат
     * @throws IOException если отсуствует файл настроек для сервиса погоды
     */
    void getWeather(City city, Period period) throws IOException;
}
