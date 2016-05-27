package com.possiblelabs.weatherapp.services;

import com.possiblelabs.weatherapp.Constants;

/**
 * Created by Brian Vega on 17/05/2016.
 */

public class WeatherService implements Constants {

    private static WeatherService instance;

    private WeatherService() {
    }

    public static WeatherService getInstance() {
        if (instance == null) {
            instance = new WeatherService();
        }
        return instance;
    }

    public void loadWeather(int cityId, GetWeatherCallback callback) {
        String request = String.format(GET_WEATHER_BY_ID_REQUEST, cityId + "", LANGUAGE_EN);
        new GetWeatherTask(callback).execute(request);
    }

    public void loadForecast3Days(int cityId, GetForecast3DaysCallback callback) {
        String request = String.format(GET_DAILY_FORECAST_BY_ID_REQUEST, cityId + "", LANGUAGE_EN);
        new GetForecast3DaysTask(callback).execute(request);
    }


}




