package tec.bd.proyectos.cli.rental;

import java.util.Date;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.RentalEntity;
import tec.bd.proyectos.errors.BadEntityException;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "loanc", description = "Creates a rental")
public class RentalCreateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<movie_id>", description = "the movie id of the new rental")
    private int movie_id;

    @CommandLine.Parameters(paramLabel = "<client_id>", description = "the client id of the new rental")
    private int client_id;

    @CommandLine.Parameters(paramLabel = "<rental_date>", description = "the rental date of the new rental")
    private Date rental_date;

    @Override
    public void run() {
        try {
            APP_CONTEXT.rentalService.createEntry(new RentalEntity(rental_date, client_id, movie_id));
        } catch (BadEntityException|ReadOnlyEntityException e) {
            System.out.println(new ExceptionReformatter(e).getFormattedMessage());
        }
    }
}
