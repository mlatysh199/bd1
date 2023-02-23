package tec.bd.weather;

import tec.bd.weather.service.WeatherService;

public class WeatherReport {

    private final static int ZIP_CODE_MAX_VALUE = 100000;

    private final WeatherService openWeatherService;

    public WeatherReport(WeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    public Report byZipCode(int zipCode) {

        if (zipCode < 0) {
            throw new IllegalArgumentException("Negative values are not allowed");
        }

        if (zipCode > ZIP_CODE_MAX_VALUE) {
            throw new IllegalArgumentException("Zip code out of range");
        }

        return this.openWeatherService.getByZipCode(String.valueOf(zipCode));
    }


    public Report byCity(String city) {
        return this.openWeatherService.getByCity(city);
    }
}
