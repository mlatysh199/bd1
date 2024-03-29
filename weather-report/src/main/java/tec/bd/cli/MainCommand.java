package tec.bd.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(
        name = "WeatherReport",
        subcommands = {
//                WeatherCommand.class,
                ZipCodeCommand.class,
                HelpCommand.class,
                IMNCityCommand.class,
                IMNZipCodeCommand.class,
                CityCommand.class,
                GUICommand.class
        },
        description = "Weather forecasts for cities around the world")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
