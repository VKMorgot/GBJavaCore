package lesson8.homework.weather_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class Locality {
    private String name;

    public String getName() {
        return name;
    }
}
