package tec.bd.proyectos.cli.review;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "revd", description = "Deletes a review")

public class ReviewDeleteCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the review to delete")
    private int id;

    @Override
    public void run() {
        try {
            APP_CONTEXT.reviewService.dropEntry(id);
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            System.out.println(new ExceptionReformatter(e).getFormattedMessage());
        }
    }
    
}
