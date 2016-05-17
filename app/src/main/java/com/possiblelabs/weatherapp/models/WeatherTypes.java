package com.possiblelabs.weatherapp.models;

/**
 * Created by possiblelabs on 5/12/16.
 * http://openweathermap.org/weather-conditions
 */
public enum WeatherTypes {

    CLEAR_SKY("01"),
    FEW_CLOUDS("02"),
    SCATTERED_CLOUDS("03"),
    BROKEN_CLOUDS("04"),
    SHOWER_RAIN("09"),
    RAIN("10"),
    THUNDERSTORM("11"),
    SNOW("13"),
    MIST("50");

    private String code;

    WeatherTypes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getIcon(boolean day) {
        if (day)
            return this.name().toLowerCase() + "_day";
        else
            return this.name().toLowerCase() + "_night";
    }

}
