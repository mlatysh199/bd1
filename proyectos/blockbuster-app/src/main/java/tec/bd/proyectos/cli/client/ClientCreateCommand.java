package tec.bd.proyectos.cli.client;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.ClientEntity;
import tec.bd.proyectos.errors.BadEntityException;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "clic", description = "Creates a client")
public class ClientCreateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<name>", description = "the name of the new client")
    private String name;

    @CommandLine.Parameters(paramLabel = "<lastname>", description = "the last name of the new client")
    private String lastname;

    @CommandLine.Parameters(paramLabel = "<email>", description = "the email of the new client")
    private String email;

    @CommandLine.Parameters(paramLabel = "<phone_number>", description = "the phone number of the new client", defaultValue = "")
    private String phone_number;

    @Override
    public void run() {
        try {
            APP_CONTEXT.clientService.createEntry(new ClientEntity(name, lastname, email, phone_number));
        } catch (BadEntityException|ReadOnlyEntityException e) {
            System.out.println(new ExceptionReformatter(e).getFormattedMessage());
        }
    }
}
