package com.possiblelabs.weatherapp.services;

import com.possiblelabs.weatherapp.models.Weather;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

/**
 * Created by BrianDennis on 03/07/2015.
 */
public class GetWeatherJSONResponseHandler extends BaseResponseHandler implements ResponseHandler<Weather> {

    @Override
    public Weather handleResponse(HttpResponse response) throws IOException {
        Weather weather = new Weather();
        String jsonResponse = new BasicResponseHandler().handleResponse(response);
        try {
            JSONObject baseObject = (JSONObject) new JSONTokener(jsonResponse).nextValue();

            weather.setName(baseObject.getString("name"));
            weather.setId(baseObject.getLong("id"));
            weather.setCod(baseObject.getInt("cod"));

            loadMainData(baseObject, weather);
            loadWeather(baseObject, weather);

            JSONObject coordObj = baseObject.getJSONObject("coord");
            weather.setLat((float) coordObj.getDouble("lat"));
            weather.setLng((float) coordObj.getDouble("lon"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}

