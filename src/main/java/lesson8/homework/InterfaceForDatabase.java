package lesson8.homework;

import lesson8.homework.weather_data.Weather;

import java.io.IOException;
import java.util.ArrayList;

public class InterfaceForDatabase {

    private final DatabaseRepository databaseRepository = new SQLiteImplementation();
    private ArrayList<Weather> weatherArrayList;

    public InterfaceForDatabase() throws IOException {
    }

    private void printWeatherFromDB() {
        if (weatherArrayList.size() == 0) {
            System.out.println("По вашему запросу ничего не найдено. Введите другие данные.");
        } else {
            for (Weather weather : weatherArrayList) {
                System.out.printf("%s в городе %s будет %s, температура: %1.0f °С. Ощущается как %1.0f °С\n",
                        weather.getForecasts().get(0).getDate(),
                        weather.getGeoObject().getLocalityObject().getName(),
                        weather.getForecasts().get(0).getPartsObject().getDayShortObject().getCondition(),
                        weather.getForecasts().get(0).getPartsObject().getDayShortObject().getTemp(),
                        weather.getForecasts().get(0).getPartsObject().getDayShortObject().getFeelsLike());
            }
        }
    }


    public void printAllData() throws MyObjectSaveException {
        weatherArrayList = databaseRepository.getAllSavedData();
        printWeatherFromDB();
    }

    public void printCityDateData(String cityName, String date) throws MyObjectSaveException {
        weatherArrayList = databaseRepository.getCitySavedDataForDate(cityName, date);
        printWeatherFromDB();
    }

    public void printDateData(String date) throws MyObjectSaveException {
        weatherArrayList = databaseRepository.getSavedDataForDate(date);
        printWeatherFromDB();
    }
}
