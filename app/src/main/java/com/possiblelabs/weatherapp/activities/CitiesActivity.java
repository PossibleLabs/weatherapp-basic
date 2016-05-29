package com.possiblelabs.weatherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.possiblelabs.weatherapp.R;
import com.possiblelabs.weatherapp.db.City;
import com.possiblelabs.weatherapp.db.CityDAO;
import com.possiblelabs.weatherapp.gui.CityAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by possiblelabs
 */
public class CitiesActivity extends BaseActivity {

    private List<City> cities;
    private List<City> filtered;
    private ListView lstCities;
    private CityAdapter cityAdapter;
    private CityDAO cityDAO;

    private ImageButton btnSearch;
    private EditText ediSearch;

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            City city = filtered.get(position);
            Intent data = new Intent(CitiesActivity.this, MainActivity.class);
            data.putExtra(CITY_ID, city.getId());
            setResult(CITY_REQUEST_CODE, data);
            finish();
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cities);
        lstCities = (ListView) findViewById(R.id.lst_cities);

        cityDAO = CityDAO.getInstance(this);
        cities = cityDAO.getAllCities();
        filtered = cities;
        cityAdapter = new CityAdapter(this, R.layout.single_item, filtered);
        lstCities.setAdapter(cityAdapter);
        lstCities.setOnItemClickListener(itemClickListener);

        ediSearch = (EditText) findViewById(R.id.edi_search);
        btnSearch = (ImageButton) findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = ediSearch.getText().toString();
                Log.d(TAG, "btnSearch.onClick:" + searchText);

                if (searchText == null || searchText.isEmpty()) {
                    filtered = cities;
                    Log.d(TAG, "btnSearch.Found:" + filtered.size());
                    return;
                }
                filter(searchText);
                Log.d(TAG, "btnSearch.Found:" + filtered.size());
            }
        });
    }

    private void filter(String filter) {
        filtered = new ArrayList<>();
        for (City city : cities) {
            if (city.toStringFormatted().toLowerCase().contains(filter.toLowerCase())) {
                filtered.add(city);
            }
        }
        cityAdapter = new CityAdapter(this, R.layout.single_item, filtered);
        lstCities.setAdapter(cityAdapter);
        lstCities.setOnItemClickListener(itemClickListener);
        cityAdapter.notifyDataSetChanged();
    }

}
