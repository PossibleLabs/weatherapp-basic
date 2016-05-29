package com.possiblelabs.weatherapp.services;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.possiblelabs.weatherapp.models.Weather;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by possiblelabs
 */
public class GetForecast3DaysTask extends AsyncTask<String, Void, Weather[]> {

    private AndroidHttpClient client;
    private GetForecast3DaysCallback callback;
    //Cache
    private Weather[] weathers;

    public GetForecast3DaysTask(GetForecast3DaysCallback callback) {
        this.callback = callback;
        this.client = AndroidHttpClient.newInstance("");
    }

    @Override
    protected Weather[] doInBackground(String... params) {
        HttpGet request = new HttpGet(params[0]);
        GetForecast3DaysJSONResponseHandler responseHandler = new GetForecast3DaysJSONResponseHandler();
        try {
            return client.execute(request, responseHandler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Weather[] result) {
        if (null != client)
            client.close();
        callback.loadForecast3Days(result);
        weathers = result;
    }
}
