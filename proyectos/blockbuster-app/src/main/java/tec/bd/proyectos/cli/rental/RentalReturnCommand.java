package tec.bd.proyectos.cli.rental;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.IDNotFoundException;

@CommandLine.Command(name = "loanr", description = "Returns a or all rentals")
public class RentalReturnCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the rental to return", defaultValue = "return_all")
    private String id;

    @Override
    public void run() {
        if (id.equals("return_all")) {
            for (var rental : APP_CONTEXT.rentalService.getAllEntires()) {
                System.out.println(rental.serialize());
            }
        } else {
            int id = Integer.parseInt(this.id);
            try {
                var rental = APP_CONTEXT.rentalService.getEntry(id);
                System.out.println(rental.serialize());
            } catch (IDNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
}
