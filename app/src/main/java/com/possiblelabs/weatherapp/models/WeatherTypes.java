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

    private static String getSuffixIcon(String base) {
        if (base.equalsIgnoreCase("n"))
            return "_night";
        else
            return "_day";
    }

    public static String getWeatherTypeFromIcon(String icon) {
        for (WeatherTypes wt : WeatherTypes.values()) {
            String prefix = icon.substring(0, 2);
            String suffix = icon.substring(2, 3);
            if (wt.code.equalsIgnoreCase(prefix))
                return wt.name().toLowerCase() + getSuffixIcon(suffix);
        }
        return null;
    }


}
