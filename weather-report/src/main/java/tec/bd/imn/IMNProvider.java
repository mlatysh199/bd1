package tec.bd.imn;

import java.util.Date;

import tec.bd.weather.model.Report;
import tec.bd.weather.model.ReportType;
import tec.bd.weather.service.WeatherProvider;

public class IMNProvider implements WeatherProvider {
    public Report getByCity(String city) {
        var report = new Report();
        report.setTemperature((float)Math.random()*60 - 10);
        report.setHumidity((float)Math.random()*100);
        report.setPressure((float)Math.random()*143 + 870);
        report.setTempMax((float)Math.random()*60 - 10);
        report.setTempMin((float)Math.random()*60 - 10);
        report.setType(ReportType.CITY);
        report.setData(city);
        report.setDate(new Date(System.currentTimeMillis()));
        return report;
    }

    public Report getByZipCode(String zip) {
        var report = new Report();
        report.setTemperature((float)Math.random()*60 - 10);
        report.setHumidity((float)Math.random()*100);
        report.setPressure((float)Math.random()*143 + 870);
        report.setTempMax((float)Math.random()*60 - 10);
        report.setTempMin((float)Math.random()*60 - 10);
        report.setType(ReportType.ZIP_CODE);
        report.setDate(new Date(System.currentTimeMillis()));
        report.setData(zip);
        return report;
    }
}
