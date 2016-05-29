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
 * Created by possiblelabs
 */
public class GetForecast3DaysJSONResponseHandler extends BaseResponseHandler implements ResponseHandler<Weather[]> {

    @Override
    public Weather[] handleResponse(HttpResponse response) throws IOException {
        Weather[] weathers = new Weather[3];
        Weather w1 = new Weather();
        Weather w2 = new Weather();
        Weather w3 = new Weather();

        String jsonResponse = new BasicResponseHandler().handleResponse(response);
        try {
            JSONObject baseObject = (JSONObject) new JSONTokener(jsonResponse).nextValue();

            JSONArray list = baseObject.getJSONArray("list");

            if (list.length() != 3)
                return null;

            loadMainDataFromForecast(list.getJSONObject(0), w1);
            loadWeather(list.getJSONObject(0), w1);

            loadMainDataFromForecast(list.getJSONObject(1), w2);
            loadWeather(list.getJSONObject(1), w2);

            loadMainDataFromForecast(list.getJSONObject(2), w3);
            loadWeather(list.getJSONObject(2), w3);

            weathers[0] = w1;
            weathers[1] = w2;
            weathers[2] = w3;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weathers;
    }
}
