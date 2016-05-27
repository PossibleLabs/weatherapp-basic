package com.possiblelabs.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 16/05/2016.
 */
public class CityDAO {

    private CityDatabaseHelper dbHelper;
    private SQLiteDatabase citiesDatabase;
    private static CityDAO instance = null;

    private CityDAO(Context context) {
        dbHelper = new CityDatabaseHelper(context);
        citiesDatabase = initializeDB();
    }

    public static CityDAO getInstance(Context context) {
        if (instance == null)
            instance = new CityDAO(context);
        return instance;
    }

    public SQLiteDatabase initializeDB() {
        try {
            dbHelper.createDataBase();
        } catch (IOException ioe) {
            Log.d("DB", "Unable to create database");
        }
        try {
            dbHelper.openDataBase();
        } catch (Exception e) {
            Log.d("DB", "Error trying to open de database");
        }
        return dbHelper.getDatabase();
    }

    public List<City> getCitiesByCountry(String countryCode) {
        List<City> cities = new ArrayList<>();
        String[] fields = new String[]{"id, nm, lat, lon, countryCode"};
        String[] args = new String[]{countryCode};
        Cursor c = citiesDatabase.query("city", fields, "countryCode=?", args, null, null, null);
        if (c.moveToFirst()) {
            do {
                City city = createCity(c);
                cities.add(city);
            } while (c.moveToNext());
        }
        return cities;
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        String[] fields = new String[]{"id, nm, lat, lon, countryCode"};
        Cursor c = citiesDatabase.query("city", fields, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                City city = createCity(c);
                cities.add(city);
            } while (c.moveToNext());
        }
        return cities;
    }

    public City getCityById(int cityId) {
        String[] fields = new String[]{"id, nm, lat, lon, countryCode"};
        String[] args = new String[]{cityId + ""};
        Cursor c = citiesDatabase.query("city", fields, "id=?", args, null, null, null);
        if (c.moveToFirst()) {
            City city = createCity(c);
            return city;
        }
        return null;
    }

    public void deleteCity(City city) {
        citiesDatabase.execSQL("DELETE FROM city WHERE id=" + city.getId());
    }

    public void updateCity(City city) {
        ContentValues values = new ContentValues();
        values.put("nm", city.getName());
        values.put("lat", city.getLatitude());
        values.put("lon", city.getLongitude());
        citiesDatabase.update("city", values, "id=" + city.getId(), null);
    }

    public City createCity(Cursor c) {
        int id = c.getInt(0);
        String nm = c.getString(1);
        double lat = c.getDouble(2);
        double lng = c.getDouble(3);
        String code = c.getString(4);
        return new City(id, nm, lat, lng, code);
    }
}
