package tec.bd.imn;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import tec.bd.weather.model.Report;

public class TestIMNProvider {
    @Test
    public void GivenZipCode_SeeIfReportValues_InValidRange() {
        var imn = new IMNProvider();
        Report rep = imn.getByZipCode("12323");
        assertTrue(rep.getHumidity() >= 0 && rep.getHumidity() <= 100);
        assertTrue(rep.getPressure() > 800 && rep.getPressure() < 1500);
        assertTrue(rep.getTemperature() < 100 && rep.getTemperature() > -50);
        assertTrue(rep.getTempMax() < 100 && rep.getTempMax() > -50);
        assertTrue(rep.getTempMin() < 100 && rep.getTempMin() > -50);
    }

    @Test
    public void GivenCity_SeeIfReportValues_InValidRange() {
        var imn = new IMNProvider();
        Report rep = imn.getByCity("moscow");
        assertTrue(rep.getHumidity() >= 0 && rep.getHumidity() <= 100);
        assertTrue(rep.getPressure() > 800 && rep.getPressure() < 1500);
        assertTrue(rep.getTemperature() < 100 && rep.getTemperature() > -50);
        assertTrue(rep.getTempMax() < 100 && rep.getTempMax() > -50);
        assertTrue(rep.getTempMin() < 100 && rep.getTempMin() > -50);
    }
}
