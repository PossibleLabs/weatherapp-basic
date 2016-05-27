package com.possiblelabs.weatherapp.models;

import com.possiblelabs.weatherapp.Constants;
import com.possiblelabs.weatherapp.utils.ConverterUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by BrianDennis on 03/07/2015.
 * but Refactored by Alvaro
 */
public class Weather implements Constants {

    //base properties
    private long id;
    private String name;
    private int cod;

    //main properties
    private double tempMax;
    private double tempMin;
    private double currentTemperature;
    private double pressure;
    private double humidity;

    //weather properties
    private int weatherId;
    private String main;
    private String description;
    private String icon;

    //weather coord
    private float lat;
    private float lng;

    public Weather() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPressure() {
        return pressure;
    }

    public String getPressureFormatted() {
        return pressure + PRESSURE_MEASURE;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getHumidityFormatted() {
        return humidity + PERCENTAGE;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public String getCurrentTemperatureString() {
        NumberFormat nf = new DecimalFormat("##.#");
        return nf.format(currentTemperature);
    }

    public String getMinTemperatureFormatted() {
        return ConverterUtil.kelvinToCelsius(tempMin) + DEGREES_MEASURE;
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getCurrentTemperatureFormatted() {
        return ConverterUtil.kelvinToCelsius(currentTemperature) + DEGREES_MEASURE;
    }

    public String getMaxTemperatureFormatted() {
        return ConverterUtil.kelvinToCelsius(tempMax) + DEGREES_MEASURE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Weather{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", cod=").append(cod);
        sb.append(", tempMax=").append(tempMax);
        sb.append(", tempMin=").append(tempMin);
        sb.append(", currentTemperature=").append(currentTemperature);
        sb.append(", pressure=").append(pressure);
        sb.append(", humidity=").append(humidity);
        sb.append(", weatherId=").append(weatherId);
        sb.append(", main='").append(main).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lng=").append(lng);
        sb.append('}');
        return sb.toString();
    }
}
