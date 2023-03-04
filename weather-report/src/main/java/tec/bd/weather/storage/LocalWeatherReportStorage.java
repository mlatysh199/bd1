package tec.bd.weather.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import tec.bd.weather.model.Report;

public class LocalWeatherReportStorage implements WeatherReportStorage {

    private HashMap<String, Report> savedReports;
    private final boolean saveWithDate;

    public LocalWeatherReportStorage() {
        saveWithDate = true;
        savedReports = new HashMap<String, Report>();
    }

    public LocalWeatherReportStorage(boolean saveWithDate) {
        this.saveWithDate = saveWithDate;
        savedReports = new HashMap<String, Report>();
    }

    @Override
    public void save(Report report) {
        String reportKey = createKey(report.getData(), report.getDate());
        savedReports.put(reportKey, report.clone());
    }

    @Override
    public void remove(String reportKey) {
        if (savedReports.containsKey(reportKey)) savedReports.remove(reportKey);
    }

    @Override
    public Report update(Report oldReport) {
        String reportKey = createKey(oldReport.getData(), oldReport.getDate());
        if (!savedReports.containsKey(reportKey)) return null;
        return savedReports.get(reportKey);
    }

    @Override
    public Report find(String reportKey) {
        if (!savedReports.containsKey(reportKey)) return null;
        return savedReports.get(reportKey);
    }

    @Override
    public List<Report> find() {
        return new ArrayList<Report>(savedReports.values());
    }

    @Override
    public String createKey(String data, Date date) {
        return data + "---" + (saveWithDate ? date : "");
    }
}
