package lesson8.homework;

import java.io.IOException;

/**
 * Город для поиска погоды
 */
public class City {

    private final String cityName;
    private String cityLocation = "";
    private String cityLatitude;
    private String cityLongitude;

    public City(String cityName) throws IOException {
        // храним имя города с заглавной буквы
        this.cityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);

        YandexCityProvider yandexCityProvider = new YandexCityProvider();
        yandexCityProvider.getCoordinates(this);
    }

    /**
     * Определяем, был ли найден город
     *
     * @return true, если город был найден
     */
    public boolean isFound() {
        return !cityLocation.isEmpty();
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityLatitude() {
        return cityLatitude;
    }

    public void setCityLatitude(String cityLatitude) {
        this.cityLatitude = cityLatitude;
    }

    public String getCityLongitude() {
        return cityLongitude;
    }

    public void setCityLongitude(String cityLongitude) {
        this.cityLongitude = cityLongitude;
    }
}
