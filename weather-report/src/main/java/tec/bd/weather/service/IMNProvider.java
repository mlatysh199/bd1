package tec.bd.weather.service;

import tec.bd.weather.Report;

public class IMNProvider implements WeatherService {
    public Report getByCity(String city) {
        var report = new Report();
        report.setTemperature((float)Math.random()*60 - 10);
        report.setHumidity((float)Math.random()*100);
        report.setPressure((float)Math.random()*143 + 870);
        report.setTemp_max((float)Math.random()*60 - 10);
        report.setTemp_min((float)Math.random()*60 - 10);
        return report;
    }

    public Report getByZipCode(String zip) {
        var report = new Report();
        report.setTemperature((float)Math.random()*60 - 10);
        report.setHumidity((float)Math.random()*100);
        report.setPressure((float)Math.random()*143 + 870);
        report.setTemp_max((float)Math.random()*60 - 10);
        report.setTemp_min((float)Math.random()*60 - 10);
        return report;
    }

    @Override
    public float getTemperature(int zipCode) {
        return getByZipCode(String.valueOf(zipCode)).getTemperature();
    }
}
