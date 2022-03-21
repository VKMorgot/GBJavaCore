package lesson8.homework.weather_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class Fact {
    private float temp;
    @JsonProperty(value = "feels_like")
    private float feelsLike;
    private String condition;

    public float getTemp() {
        return temp;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public String getCondition() {
        return condition;
    }
}
