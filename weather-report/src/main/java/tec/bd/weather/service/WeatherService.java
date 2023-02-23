package tec.bd.weather.service;

import tec.bd.weather.Report;

public interface WeatherService {

    float getTemperature(int zipCode);

    Report getByCity(String city);

    Report getByZipCode(String zipCode);
}
