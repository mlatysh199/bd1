package tec.bd.proyectos.cli.log;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;

@CommandLine.Command(name = "log", description = "Get the last N logs")
public class LogCommand implements Runnable {

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<n-logs>", description = "number of logs to get")
    private int numberOfLogs;

    @Override
    public void run() {
        var logs = APP_CONTEXT.logService.getAllEntires();
        for (int i = 0; i < logs.size() && i < numberOfLogs; i++) {
            System.out.println(logs.get(i).serialize());
        }
    }
}
