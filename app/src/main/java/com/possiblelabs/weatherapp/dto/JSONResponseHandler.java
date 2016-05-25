package com.possiblelabs.weatherapp.dto;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
public class JSONResponseHandler implements ResponseHandler<Weather> {

    @Override
    public Weather handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        Weather result = new Weather();
        String JSONResponse = new BasicResponseHandler().handleResponse(response);
        try {
            JSONObject responseObject = (JSONObject) new JSONTokener(JSONResponse).nextValue();

            JSONObject mainObject = responseObject.getJSONObject("main");
            JSONArray mainObject2 = responseObject.getJSONArray("weather");
            JSONObject object2Weather = (JSONObject) mainObject2.get(0);
            result.setTemperature_actual(mainObject.getDouble("temp"));
            result.setTemperature_min(mainObject.getDouble("temp_min"));
            result.setTemperature_maxima(mainObject.getDouble("temp_max"));
            result.setHumidity(mainObject.getDouble("humidity"));
            result.setPrecision(mainObject.getDouble("pressure"));
            result.setDescription(object2Weather.getString("description"));
            JSONArray country = responseObject.getJSONArray("name");
            result.setCountry(country.getString(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

