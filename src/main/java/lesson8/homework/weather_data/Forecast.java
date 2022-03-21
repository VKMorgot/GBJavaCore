package lesson8.homework.weather_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public
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
