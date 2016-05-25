package com.possiblelabs.weatherapp.dto;

/**
 * Created by BrianDennis on 03/07/2015.
 */
public class Weather implements Constans {

    private double temperatureMax;
    private double temperaturaMin;
    private double temperatureActual;
    private String description;
    private double precion;
    private double humidity;
    private String country;

    public Weather() {
    }

    public Weather(double temperatureMax, double temperatureMin, double temperatureActual, String description,
                   double precion, double humidity, String pais) {
        this.temperatureMax = temperatureMax;
        this.temperaturaMin = temperatureMin;
        this.temperatureActual = temperatureActual;
        this.description = description;
        this.precion = precion;
        this.humidity = humidity;
        this.country = pais;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public String getPrecion() {
        return precion + PRECITION;
    }

    public void setPrecision(double precion) {
        this.precion = precion;
    }

    public String getHumidity() {
        return humidity + PORCETAJE;
    }

    public void setHumidity(double humedad) {
        this.humidity = humedad;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperature_maxima(double temperatura_maxima) {
        this.temperatureMax = temperatura_maxima;
    }

    public double getTemperaturaMin() {
        return temperaturaMin;
    }

    public void setTemperature_min(double temperatura_minima) {
        this.temperaturaMin = temperatura_minima;
    }

    public double getTemperatureActual() {
        return temperatureActual;
    }

    public String getmin() {
        return Converter.kelvinToCelsius(temperaturaMin) + GRADOS;
    }

    public void setTemperature_actual(double temperatura_actual) {
        this.temperatureActual = temperatura_actual;
    }

    public String getactual() {
        return Converter.kelvinToCelsius(temperatureActual) + GRADOS;
    }

    public String getmax() {
        return Converter.kelvinToCelsius(temperatureMax) + GRADOS;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
