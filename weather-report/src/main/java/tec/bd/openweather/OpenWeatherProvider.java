package tec.bd.openweather;

import retrofit2.Call;
import tec.bd.weather.model.Report;
import tec.bd.weather.model.ReportType;
import tec.bd.weather.service.WeatherProvider;

import java.util.Map;

public class OpenWeatherProvider implements WeatherProvider {

    private final String API_KEY;

    private final OpenWeatherResource openWeatherResource;

    public OpenWeatherProvider(OpenWeatherResource openWeatherResource, OpenWeatherAPIProvider openWeatherAPIProvider) {
        this.openWeatherResource = openWeatherResource;
        API_KEY = openWeatherAPIProvider.getAPIKey();
    }

    private Map<String, String> queryStringZipOptions(String zipCode) {
        return Map.of("zip", zipCode, "appId", API_KEY);
    }

    private Map<String, String> queryStringCityOptions(String cityName) {
        return Map.of("q", cityName, "appId", API_KEY);
    }

    @Override
    public Report getByCity(String city) {
        try {
            var options = queryStringCityOptions(city);
            Call<OpenWeatherReport> openWeatherReportCall = this.openWeatherResource.get(options);
            OpenWeatherReport openWeatherReport = openWeatherReportCall.execute().body();
            Report report = openWeatherReport.getMain().getReport();
            report.setType(ReportType.CITY);
            report.setData(city);
            return report;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error when calling open weather remote API");
        }
    }

    @Override
    public Report getByZipCode(String zipCode) {
        try {
            var options = queryStringZipOptions(zipCode);
            Call<OpenWeatherReport> openWeatherReportCall = this.openWeatherResource.get(options);
            OpenWeatherReport openWeatherReport = openWeatherReportCall.execute().body();
            Report report = openWeatherReport.getMain().getReport();
            report.setType(ReportType.ZIP_CODE);
            report.setData(zipCode);
            return report;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error when calling open weather remote API");
        }
    }
}
