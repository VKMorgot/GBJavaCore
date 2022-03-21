package lesson8.homework.weather_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public
class Geo {
    @JsonProperty(value = "locality")
    private Locality localityObject;

    public Locality getLocalityObject() {
        return localityObject;
    }
}
