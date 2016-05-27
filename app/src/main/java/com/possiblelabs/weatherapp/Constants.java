package com.possiblelabs.weatherapp;

/**
 * Created by Alexandra on 16/05/2016.
 */
public interface Constants {
    public static final String TAG = "WEATHER_APP";
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "weather_database.sqlite";

    public static final String DEGREES_MEASURE = " Grados C";
    public static final String PRESSURE_MEASURE = " mb";
    public static final String PERCENTAGE = "%";

    //SERVICE CONFIGURATION
    public static final String OPEN_WEATHER_APP_ID = "17951532fcfc8ed6c64106273a4fbdb6";//TODO: re-generate this before to deploy to Google Play Store.
    public static final String LANGUAGE_SP = "sp";
    public static final String LANGUAGE_EN = "en";
    public static final String UNITS = "metric";
    public static final int DEFAULT_CITY_ID = 3919968;//COCHABAMBA
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final int DEFAULT_LINES = 24;//each 3 hours = 8 at day = 24 for 3 next days.
    public static final int DEFAULT_DAYS = 3;
    public static final String GET_WEATHER_BY_ID_REQUEST = BASE_URL + "weather?id=%s&lang=%s&units=" + UNITS + "&APPID=" + OPEN_WEATHER_APP_ID;
    public static final String GET_DAILY_FORECAST_BY_ID_REQUEST = BASE_URL + "forecast/daily?id=%s&lang=%s&units=" + UNITS + "&cnt=" + DEFAULT_DAYS + "&APPID=" + OPEN_WEATHER_APP_ID;
    public static final String GET_FORECAST_BY_ID_REQUEST = BASE_URL + "forecast?id=%s&lang=%s&units=" + UNITS + "&cnt=" + DEFAULT_LINES + "&APPID=" + OPEN_WEATHER_APP_ID;
    public static final String GET_WEATHER_BY_QUERY_REQUEST = BASE_URL + "weather?q=%s&lang=%s&units=" + UNITS + "&APPID=" + OPEN_WEATHER_APP_ID;
}
