package com.possiblelabs.weatherapp.dto;

import android.content.Context;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import com.possiblelabs.weatherapp.R;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by Brian Vega on 17/05/2016.
 */

public class ServiceWether {

    private Weather weather;
    private static ServiceWether instance;
    private String url;
    private GetWeather getWeather;

    public ServiceWether() {

    }

    public static ServiceWether getInstance() {
        if (instance == null) {
            instance = new ServiceWether();
        }
        return instance;
    }

    public void loadWeather(String cityId, Context context, final GetWeather callback) {
        url = context.getResources().getString(R.string.UrlOpenWeatherIdCityDefault, cityId, context.getResources().getString(R.string.keyOpenWeatherApp));
        getWeather = callback;
        new HttpGetTask().execute(url);

    }

    public void loadWeatherTest(final GetWeather callback) {
        url = "http://api.openweathermap.org/data/2.5/weather?id=2172797&APPID=17951532fcfc8ed6c64106273a4fbdb6";
        new HttpGetTask().execute(url);
        getWeather = callback;
    }

    public class HttpGetTask extends AsyncTask<String, Void, Weather> {

        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");

        @Override
        protected Weather doInBackground(String... params) {

            HttpGet request = new HttpGet(params[0]);
            JSONResponseHandler responseHandler = new JSONResponseHandler();
            try {
                return mClient.execute(request, responseHandler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Weather result) {
            if (null != mClient)
                mClient.close();
           // weather = result;
            getWeather.loadWeather(result);
        }

    }
}




