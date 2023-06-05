package tec.bd.proyectos.cli.rental;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "loand", description = "Deletes a rental")

public class RentalDeleteCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the rental to delete")
    private int id;

    @Override
    public void run() {
        try {
            APP_CONTEXT.rentalService.dropEntry(id);
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
    
}
