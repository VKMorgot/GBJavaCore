package lesson8.homework.weather_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class Weather {

    @JsonProperty(value = "now_dt")
    private String nowDt;
    @JsonProperty(value = "geo_object")
    private Geo getObject;
    @JsonProperty(value = "fact")
    Fact factObject;
    ArrayList<Forecast> forecasts = new ArrayList<>();

    public Geo getGetObject() {
        return getObject;
    }

    public String getNowDt() {
        return nowDt;
    }

    public Fact getFactObject() {
        return factObject;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

}
