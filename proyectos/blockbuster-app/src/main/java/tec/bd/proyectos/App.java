package tec.bd.proyectos;

import picocli.CommandLine;
import tec.bd.proyectos.cli.MainCommand;

public class App {

    public static void main(String[] args) {

        CommandLine cmd = new CommandLine(new MainCommand());
        cmd.setExecutionStrategy(new CommandLine.RunAll());
        cmd.execute(args);

    }
}