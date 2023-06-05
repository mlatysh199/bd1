package tec.bd.proyectos.cli.client;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.ClientEntity;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "cliu", description = "Updates a client")
public class ClientUpdateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the client to update")
    private int id;

    @CommandLine.Parameters(paramLabel = "<name>", description = "the new name of the client to update")
    private String name;

    @CommandLine.Parameters(paramLabel = "<lastname>", description = "the new last name of the client to update")
    private String lastname;

    @CommandLine.Parameters(paramLabel = "<email>", description = "the new email of the client to update")
    private String email;

    @CommandLine.Parameters(paramLabel = "<phone_number>", description = "the new phone number of the client to update", defaultValue = "")
    private String phone_number;

    @Override
    public void run() {
        try {
            APP_CONTEXT.clientService.updateEntry(new ClientEntity(id, name, lastname, email, phone_number));
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
    
}
