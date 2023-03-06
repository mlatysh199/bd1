package tec.bd.weather.storage;

import java.util.Date;
import java.util.List;

import tec.bd.weather.model.Report;

public interface WeatherReportStorage {
    void save(Report report);

    void remove(String reportKey);

    Report update(Report newReport);

    Report find(String reportKey);

    List<Report> find();

    String createKey(String data, Date date);
}
