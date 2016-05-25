package com.possiblelabs.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.possiblelabs.weatherapp.db.CityDAO;
import com.possiblelabs.weatherapp.dto.GetWeather;
import com.possiblelabs.weatherapp.dto.ServiceWether;
import com.possiblelabs.weatherapp.dto.Weather;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView txtToday;
    private TextView txtCurrentDate;
    private TextView txtCurrentHours;
    private TextView txtCurrentMinutes;
    private TextView txtNextDay1;
    private TextView txtNextDay2;
    private TextView txtNextDay3;
    private ImageView imgWeather;

    private RelativeLayout mainContent;
    private LinearLayout topContent;
    private LinearLayout bottomContent;

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

        cityDAO = CityDAO.getInstance(this);
        String url = "http://api.openweathermap.org/data/2.5/weather?id=" + "2172797" + "&lang=es&APPID=17951532fcfc8ed6c64106273a4fbdb6";
        ServiceWether.getInstance().loadWeatherTest(new GetWeather() {
            @Override
            public void loadWeather(Weather weather) {
                Log.d("WeatherDescription", weather.getDescription());
            }
        });
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

    private void loadDayNight(int hours) {
        if ((hours >= 0 && hours <= 6) || (hours >= 19 && hours <= 23)) {
            topContent.setBackgroundColor(getResources().getColor(R.color.dark_night));
            mainContent.setBackgroundColor(getResources().getColor(R.color.dark_night_light));
            bottomContent.setBackgroundColor(getResources().getColor(R.color.dark_night_dark));
            imgWeather.setImageResource(R.drawable.clear_sky_night);

        } else {
            topContent.setBackgroundColor(getResources().getColor(R.color.sky_blue_dark));
            mainContent.setBackgroundColor(getResources().getColor(R.color.sky_blue));
            bottomContent.setBackgroundColor(getResources().getColor(R.color.sky_blue_dark_black));
            imgWeather.setImageResource(R.drawable.clear_sky_day);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        loadToday();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
