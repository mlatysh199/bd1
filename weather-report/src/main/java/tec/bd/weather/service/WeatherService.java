package tec.bd.weather.service;

import java.util.Date;

import tec.bd.weather.model.Report;
import tec.bd.weather.storage.WeatherReportStorage;

public class WeatherService {

    private final WeatherProvider weatherProvider;
    private final WeatherReportStorage weatherReportStorage;

    public WeatherService(WeatherProvider weatherProvider, WeatherReportStorage weatherReportStorage) {
        this.weatherProvider = weatherProvider;
        this.weatherReportStorage = weatherReportStorage;
    }

    public Report byZipCode(int zipCode) {

        String reportKey = weatherReportStorage.createKey(String.valueOf(zipCode), new Date(System.currentTimeMillis()));
        var foundReport = weatherReportStorage.find(reportKey);

        if (foundReport != null) return foundReport;

        var report = weatherProvider.getByZipCode(String.valueOf(zipCode));

        weatherReportStorage.save(report);
        return report;
    }


    public Report byCity(String city) {
        String reportKey = weatherReportStorage.createKey(city, new Date(System.currentTimeMillis()));
        var foundReport = weatherReportStorage.find(reportKey);

        if (foundReport != null) return foundReport;

        var report = weatherProvider.getByCity(city);

        weatherReportStorage.save(report);
        return report;
    }
}
