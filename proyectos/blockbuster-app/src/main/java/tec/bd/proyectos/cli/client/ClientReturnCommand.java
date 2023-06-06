package tec.bd.proyectos.cli.client;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.IDNotFoundException;

@CommandLine.Command(name = "clir", description = "Returns a or all clients")
public class ClientReturnCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the client to return", defaultValue = "return_all")
    private String id;

    @Override
    public void run() {
        if (id.equals("return_all")) {
            for (var client : APP_CONTEXT.clientService.getAllEntires()) {
                System.out.println(client.serialize());
            }
        } else {
            int id = Integer.parseInt(this.id);
            try {
                var client = APP_CONTEXT.clientService.getEntry(id);
                System.out.println(client.serialize());
            } catch (IDNotFoundException e) {
                System.out.println(new ExceptionReformatter(e).getFormattedMessage());
            }
        }
    }
    
}
