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

    public void setDate(String date) {
        this.date = date;
    }

    public Parts setPartsObject() {
        partsObject = new Parts();
        return partsObject;
    }

    public Parts getPartsObject() {
        return partsObject;
    }
}
