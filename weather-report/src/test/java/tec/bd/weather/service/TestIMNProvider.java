package tec.bd.weather.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tec.bd.weather.Report;

public class TestIMNProvider {
    @Test
    public void GivenZipCode_SeeIfReportValues_InValidRange() {
        var imn = new IMNProvider();
        Report rep = imn.getByZipCode("12323");
        assertTrue(rep.getHumidity() >= 0 && rep.getHumidity() <= 0);
        assertTrue(rep.getPressure() > 800 && rep.getPressure() < 1500);
        assertTrue(rep.getTemperature() < 100 && rep.getTemperature() > -50);
        assertTrue(rep.getTemp_max() < 100 && rep.getTemp_max() > -50);
        assertTrue(rep.getTemp_min() < 100 && rep.getTemp_min() > -50);
    }

    @Test
    public void GivenCity_SeeIfReportValues_InValidRange() {
        var imn = new IMNProvider();
        Report rep = imn.getByCity("moscow");
        assertTrue(rep.getHumidity() >= 0 && rep.getHumidity() <= 0);
        assertTrue(rep.getPressure() > 800 && rep.getPressure() < 1500);
        assertTrue(rep.getTemperature() < 100 && rep.getTemperature() > -50);
        assertTrue(rep.getTemp_max() < 100 && rep.getTemp_max() > -50);
        assertTrue(rep.getTemp_min() < 100 && rep.getTemp_min() > -50);
    }
}
