package tec.bd.weather.service;

import tec.bd.weather.model.Report;

public interface WeatherProvider {

    Report getByCity(String city);

    Report getByZipCode(String zipCode);
}
