package com.possiblelabs.weatherapp.services;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.possiblelabs.weatherapp.models.Weather;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public class GetWeatherTask extends AsyncTask<String, Void, Weather> {

    private GetWeatherCallback callback;
    private AndroidHttpClient client;
    private static Weather weather;

    public GetWeatherTask(GetWeatherCallback callback) {
        this.callback = callback;
        this.client = AndroidHttpClient.newInstance("");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Weather doInBackground(String... params) {

        HttpGet request = new HttpGet(params[0]);
        GetWeatherJSONResponseHandler responseHandler = new GetWeatherJSONResponseHandler();
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
    protected void onPostExecute(Weather result) {
        if (null != client)
            client.close();
        callback.loadWeather(result);
        weather = result;
    }

}