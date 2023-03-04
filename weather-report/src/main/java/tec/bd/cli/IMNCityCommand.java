package tec.bd.cli;

import picocli.CommandLine;
import tec.bd.ApplicationContext;

@CommandLine.Command(name = "imn-city", description = "Get weather forecast by city from the IMN system")
public class IMNCityCommand implements Runnable{

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<city name>", description = "city name to be resolved")
    private String cityName;

    @Override
    public void run() {
        var weatherService = APP_CONTEXT.imnService;
        var report = weatherService.byCity(cityName);
        System.out.println(report.toString());
    }
    
}
