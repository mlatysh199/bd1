package tec.bd.proyectos.cli.review;

import java.util.Date;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.ReviewEntity;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "revu", description = "Updates a review")
public class ReviewUpdateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the review to update")
    private int id;

    @CommandLine.Parameters(paramLabel = "<movie_id>", description = "the new movie id of the review to update")
    private int movie_id;

    @CommandLine.Parameters(paramLabel = "<client_id>", description = "the new client id of the review to update")
    private int client_id;

    @CommandLine.Parameters(paramLabel = "<rating>", description = "the new rating of the review to update")
    private int rating;

    @CommandLine.Parameters(paramLabel = "<review_text>", description = "the new review text of the review to update")
    private String review_text;

    @CommandLine.Parameters(paramLabel = "<created_on>", description = "the new date of creation of the review to update")
    private Date created_on;

    @Override
    public void run() {
        try {
            APP_CONTEXT.reviewService.updateEntry(new ReviewEntity(id, rating, review_text, created_on, client_id, movie_id));
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            System.out.println(new ExceptionReformatter(e).getFormattedMessage());
        }
    }
    
}
