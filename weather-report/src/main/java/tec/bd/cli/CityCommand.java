package tec.bd.cli;

import picocli.CommandLine;
import tec.bd.ApplicationContext;
import tec.bd.weather.WeatherReport;

@CommandLine.Command(name = "city", description = "Get weather forecast by city")
public class CityCommand implements Runnable {

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<city name>", description = "city name to be resolved")
    private String cityName;

    @Override
    public void run() {
        var openWeatherService = APP_CONTEXT.openWeatherService;
        var weatherReport = new WeatherReport(openWeatherService);
        var report = weatherReport.byCity(cityName);
        System.out.println(report.toString());
    }

}
