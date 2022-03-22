package lesson8.homework.weather_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class Parts {
    @JsonProperty(value = "day_short")
    private DayShort dayShortObject;

    public DayShort getDayShortObject() {
        return dayShortObject;
    }

    public DayShort setDayShortObject() {
        dayShortObject = new DayShort();
        return dayShortObject;
    }

}
