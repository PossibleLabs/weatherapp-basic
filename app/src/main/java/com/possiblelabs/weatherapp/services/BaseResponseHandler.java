package com.possiblelabs.weatherapp.services;

import com.possiblelabs.weatherapp.models.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created possiblelabs
 */
public class BaseResponseHandler {

    protected void loadMainData(JSONObject baseObject, Weather weather) {
        try {
            JSONObject mainObj = baseObject.getJSONObject("main");
            weather.setCurrentTemperature(mainObj.getDouble("temp"));
            weather.setTempMin(mainObj.getDouble("temp_min"));
            weather.setTempMax(mainObj.getDouble("temp_max"));
            weather.setHumidity(mainObj.getDouble("humidity"));
            weather.setPressure(mainObj.getDouble("pressure"));
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
    }

    protected void loadWeather(JSONObject baseObject, Weather weather) {
        try {
            JSONArray weatherArray = baseObject.getJSONArray("weather");
            if (weatherArray.length() > 0) {
                JSONObject weatherObj = (JSONObject) weatherArray.get(0);
                weather.setWeatherId(weatherObj.getInt("id"));
                weather.setMain(weatherObj.getString("main"));
                weather.setDescription(weatherObj.getString("description"));
                weather.setIcon(weatherObj.getString("icon"));
            }
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
    }
}
