package com.possiblelabs.weatherapp.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.possiblelabs.weatherapp.R;
import com.possiblelabs.weatherapp.db.City;
import com.possiblelabs.weatherapp.db.CityDAO;
import com.possiblelabs.weatherapp.models.WeatherTypes;
import com.possiblelabs.weatherapp.services.GetForecast3DaysCallback;
import com.possiblelabs.weatherapp.services.GetWeatherCallback;
import com.possiblelabs.weatherapp.services.WeatherService;
import com.possiblelabs.weatherapp.models.Weather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    //GUI Components
    private TextView txtToday;
    private TextView txtCurrentDate;
    private TextView txtCurrentHours;
    private TextView txtCurrentMinutes;
    private TextView txtNextDay1;
    private TextView txtNextDay2;
    private TextView txtNextDay3;
    private ImageView imgWeather;
    private TextView txtCity;
    private TextView txtCountry;
    private TextView txtMainWeather;
    private TextView txtMainFormat;
    private TextView txtNextDay1Weather;
    private TextView txtNextDay2Weather;
    private TextView txtNextDay3Weather;

    //Containers
    private RelativeLayout mainContent;
    private LinearLayout topContent;
    private LinearLayout bottomContent;

    //Cache
    private Weather weather;

    //Services
    private WeatherService weatherService;
    private CityDAO cityDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtToday = (TextView) findViewById(R.id.txt_today);
        txtCurrentDate = (TextView) findViewById(R.id.txt_current_date);
        txtCurrentHours = (TextView) findViewById(R.id.txt_current_hours);
        txtCurrentMinutes = (TextView) findViewById(R.id.txt_current_minutes);
        txtNextDay1 = (TextView) findViewById(R.id.txt_next_day_1);
        txtNextDay2 = (TextView) findViewById(R.id.txt_next_day_2);
        txtNextDay3 = (TextView) findViewById(R.id.txt_next_day_3);
        imgWeather = (ImageView) findViewById(R.id.img_weather);
        mainContent = (RelativeLayout) findViewById(R.id.main_content);
        topContent = (LinearLayout) findViewById(R.id.top_content);
        bottomContent = (LinearLayout) findViewById(R.id.bottom_content);
        txtCity = (TextView) findViewById(R.id.txt_city);
        txtCountry = (TextView) findViewById(R.id.txt_country);
        txtMainWeather = (TextView) findViewById(R.id.txt_main_weather);
        txtMainFormat = (TextView) findViewById(R.id.txt_main_format);
        txtNextDay1Weather = (TextView) findViewById(R.id.txt_next_day_1_weather);
        txtNextDay2Weather = (TextView) findViewById(R.id.txt_next_day_2_weather);
        txtNextDay3Weather = (TextView) findViewById(R.id.txt_next_day_3_weather);

        cityDAO = CityDAO.getInstance(this);
        weatherService = WeatherService.getInstance();
    }


    private void loadToday() {
        Calendar c = Calendar.getInstance();

        int minutes = c.get(Calendar.MINUTE);
        int hours = c.get(Calendar.HOUR_OF_DAY);

        txtCurrentMinutes.setText(minutes > 9 ? minutes + "" : "0" + minutes);
        txtCurrentHours.setText(hours > 9 ? hours + "" : "0" + hours);

        SimpleDateFormat sf = new SimpleDateFormat("MMMM d", Locale.getDefault());
        String month = sf.format(c.getTime());
        txtCurrentDate.setText(month);

        String dayName = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        txtToday.setText(dayName);

        c.add(Calendar.DAY_OF_MONTH, 1);
        dayName = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        txtNextDay1.setText(dayName);

        c.add(Calendar.DAY_OF_MONTH, 1);
        dayName = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        txtNextDay2.setText(dayName);

        c.add(Calendar.DAY_OF_MONTH, 1);
        dayName = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        txtNextDay3.setText(dayName);

        loadDayNight(hours);
    }

    private int getImageResourceId() {
        if (weather == null)
            return -1;

        String imageName = WeatherTypes.getWeatherTypeFromIcon(weather.getIcon());
        return getResources().getIdentifier("drawable/" + imageName, null, getPackageName());
    }

    private void loadDayNight(int hours) {

        if ((hours >= 0 && hours <= 6) || (hours >= 19 && hours <= 23)) {
            topContent.setBackgroundColor(getResources().getColor(R.color.dark_night));
            mainContent.setBackgroundColor(getResources().getColor(R.color.dark_night_light));
            bottomContent.setBackgroundColor(getResources().getColor(R.color.dark_night_dark));
        } else {
            topContent.setBackgroundColor(getResources().getColor(R.color.sky_blue_dark));
            mainContent.setBackgroundColor(getResources().getColor(R.color.sky_blue));
            bottomContent.setBackgroundColor(getResources().getColor(R.color.sky_blue_dark_black));
        }

        int resId = getImageResourceId();

        if (resId != -1)
            imgWeather.setImageResource(resId);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadWeather();
        loadForecast();
    }

    private void loadForecast() {
        Log.d(TAG, "loadForecast");
        final City city = cityDAO.getCityById(DEFAULT_CITY_ID);
        weatherService.loadForecast3Days(city.getId(), new GetForecast3DaysCallback() {
            @Override
            public void loadForecast3Days(Weather[] weathers) {
                Log.d(TAG, "loadForecast3Days");


                if (weathers == null || weathers.length != 3)
                    return;

                for (Weather w : weathers) {
                    Log.d(TAG, "x=" + w.toString());
                }

                txtNextDay1Weather.setText(weathers[0].getCurrentTemperatureString());
                txtNextDay2Weather.setText(weathers[1].getCurrentTemperatureString());
                txtNextDay3Weather.setText(weathers[2].getCurrentTemperatureString());
            }
        });
    }

    private void loadWeather() {
        Log.d(TAG, "loadWeather");
        final City city = cityDAO.getCityById(DEFAULT_CITY_ID);
        weatherService.loadWeather(city.getId(), new GetWeatherCallback() {
            @Override
            public void loadWeather(Weather weather) {
                Log.d(TAG, "loadWeather");

                if (weather == null)
                    return;

                Log.d(TAG, "Weather=" + weather.getDescription());
                txtCity.setText(weather.getName());
                txtCountry.setText(city.getCountryCode());
                txtMainWeather.setText(weather.getCurrentTemperatureString());
                MainActivity.this.weather = weather;

                loadToday();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
