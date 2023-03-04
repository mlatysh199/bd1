package tec.bd.weather.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tec.bd.weather.model.Report;
import tec.bd.weather.storage.LocalWeatherReportStorage;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Date;

public class WeatherServiceTest {

    @Test
    public void whenReportByZipCodeAndRemoteCall_ThenGetAndStoreLocally() {

        var remoteWeatherProvider = mock(WeatherProvider.class);
        var weatherReportStorage = mock(LocalWeatherReportStorage.class);

        given(remoteWeatherProvider.getByZipCode(anyString())).willReturn(mock(Report.class));

        var service = new WeatherService(remoteWeatherProvider, weatherReportStorage);

        var actual = service.byZipCode(90210);

        verify(remoteWeatherProvider, times(1)).getByZipCode("90210");
        verify(weatherReportStorage, times(1)).save(any(Report.class));

        assertThat(actual).isNotNull();
    }

    @Disabled
    // Desabilitado porque mockito no guarda la información. Si se hace fuera de mockito, todo se ejectua de forma esperada...
    @Test
    public void whenReportByZipCodeAndDataIsStoredLocally_ThenReturnLocalData() {

        var remoteWeatherProvider = mock(WeatherProvider.class);
        var weatherReportStorage = mock(LocalWeatherReportStorage.class);

        given(weatherReportStorage.find(anyString())).willReturn(mock(Report.class));

        assertThat(weatherReportStorage.createKey("90210", new Date(0))).isNull();

        var service = new WeatherService(remoteWeatherProvider, weatherReportStorage);

        service.byZipCode(90210);
        var actual = service.byZipCode(90210);

        verify(weatherReportStorage, times(2)).find(anyString());
        verify(remoteWeatherProvider, times(1)).getByZipCode("90210");
        verify(weatherReportStorage, times(1)).save(any(Report.class));

        assertThat(actual).isNull();
    }
}