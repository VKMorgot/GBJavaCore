package lesson8.homework;

import java.io.IOException;

public interface CityProvider {
    /**
     * Поиск координат города по его названию
     *
     * @param city класс Город
     * @throws IOException если отсутствует файл настроек для гео сервиса
     */
    void getCoordinates(City city) throws IOException;
}
