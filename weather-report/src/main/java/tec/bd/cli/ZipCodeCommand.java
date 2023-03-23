package tec.bd.cli;

import picocli.CommandLine;
import tec.bd.ApplicationContext;

@CommandLine.Command(name = "-zip", description = "Get weather forecast by zip code")
public class ZipCodeCommand implements Runnable {

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<zip code>", description = "zip code to be resolved")
    private String zipCode;

    @Override
    public void run() {
        var weatherService = APP_CONTEXT.openWeatherService;
        var report = weatherService.byZipCode(Integer.valueOf(zipCode));
        System.out.println(report.toString());
    }

}
