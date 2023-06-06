package tec.bd.proyectos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine;
import tec.bd.proyectos.cli.MainCommand;

public class App {

    private final static Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {

        LOGGER.debug("Starting application"); 

        CommandLine cmd = new CommandLine(new MainCommand());
        cmd.setExecutionStrategy(new CommandLine.RunAll());
        cmd.execute(args);

    }
}