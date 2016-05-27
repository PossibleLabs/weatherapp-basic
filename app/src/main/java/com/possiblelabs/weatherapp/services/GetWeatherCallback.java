package com.possiblelabs.weatherapp.services;

import com.possiblelabs.weatherapp.models.Weather;

/**
 * Created by Brian Vega on 25/05/2016.
 */
public interface GetWeatherCallback {

    void loadWeather(Weather weather);
}
