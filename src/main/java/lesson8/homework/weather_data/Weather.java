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
    private Geo geoObject;
    @JsonProperty(value = "fact")
    private Fact factObject;
    private final ArrayList<Forecast> forecasts = new ArrayList<>();

    public Geo getGeoObject() {
        return geoObject;
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

    public ArrayList<Forecast> setForecasts() {
        forecasts.add(new Forecast());
        return forecasts;
    }

    public Geo setGeoObject() {
        geoObject = new Geo();
        return geoObject;
    }
}
