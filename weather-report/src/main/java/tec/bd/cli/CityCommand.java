package tec.bd.cli;

import picocli.CommandLine;
import tec.bd.ApplicationContext;

@CommandLine.Command(name = "city", description = "Get weather forecast by city")
public class CityCommand implements Runnable {

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<city name>", description = "city name to be resolved")
    private String cityName;

    @Override
    public void run() {
        var weatherService = APP_CONTEXT.openWeatherService;
        var report = weatherService.byCity(cityName);
        System.out.println(report.toString());
    }

}
