package tec.bd.weather.storage;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import tec.bd.weather.model.Report;

import static org.assertj.core.api.Assertions.*;

public class LocalWeatherReportStorageTest {

    /*
        Ya está implementado
     */


    @Test
    public void findReport() {
        LocalWeatherReportStorage storage = new LocalWeatherReportStorage(false);
        Report report = new Report();
        report.setData("3475982375");
        storage.save(report);
        var result = storage.find(storage.createKey("3475982375", null));
        assertThat(result).isNotNull();
    }

    @Test
    public void findAllReports() {
        LocalWeatherReportStorage storage = new LocalWeatherReportStorage(false);
        Report report = new Report();
        report.setData("3475982375");
        storage.save(report);
        report = new Report();
        report.setData("13475982375");
        storage.save(report);
        report = new Report();
        report.setData("23475982375");
        storage.save(report);
        report = new Report();
        report.setData("33475982375");
        storage.save(report);
        assertTrue(storage.find().size() == 4);
    }

    @Test
    public void saveReport() {
        LocalWeatherReportStorage storage = new LocalWeatherReportStorage(false);
        Report report = new Report();
        report.setData("3475982375");
        storage.save(report);
    }

    @Test
    public void updateReport() {
        LocalWeatherReportStorage storage = new LocalWeatherReportStorage(false);
        Report report = new Report();
        report.setData("3475982375");
        storage.save(report);
        assertThat(storage.update(report)).isNotNull();
    }

    @Test
    public void deleteReport() {
        LocalWeatherReportStorage storage = new LocalWeatherReportStorage(false);
        Report report = new Report();
        report.setData("3475982375");
        storage.save(report);
        storage.remove(storage.createKey("3475982375", null));
        assertThat(storage.find(storage.createKey("3475982375", null))).isNull();
    }
}