package tec.bd.proyectos.cli.rental;

import java.util.Date;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.RentalEntity;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "loanu", description = "Updates a rental")
public class RentalUpdateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the rental to update")
    private int id;

    @CommandLine.Parameters(paramLabel = "<client_id>", description = "the new client id of the rental to update")
    private int client_id;

    @CommandLine.Parameters(paramLabel = "<movie_id>", description = "the new movie id of the rental to update")
    private int movie_id;

    @CommandLine.Parameters(paramLabel = "<rental_date>", description = "the new rental date of the rental to update")
    private Date rental_date;

    @Override
    public void run() {
        try {
            APP_CONTEXT.rentalService.updateEntry(new RentalEntity(id, rental_date, client_id, movie_id));
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
    
}
