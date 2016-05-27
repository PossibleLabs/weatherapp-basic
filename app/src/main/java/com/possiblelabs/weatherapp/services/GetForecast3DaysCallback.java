package com.possiblelabs.weatherapp.services;

import com.possiblelabs.weatherapp.models.Weather;

/**
 * Created by possiblelabs
 */
public interface GetForecast3DaysCallback {

    void loadForecast3Days(Weather[] weather);
}
