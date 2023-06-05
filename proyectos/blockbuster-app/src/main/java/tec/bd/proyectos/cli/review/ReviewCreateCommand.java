package tec.bd.proyectos.cli.review;

import java.util.Date;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.ReviewEntity;
import tec.bd.proyectos.errors.BadEntityException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "revc", description = "Creates a review")
public class ReviewCreateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<client_id>", description = "the client id of the new review")
    private int client_id;

    @CommandLine.Parameters(paramLabel = "<movie_id>", description = "the movie id of the new review")
    private int movie_id;

    @CommandLine.Parameters(paramLabel = "<rating>", description = "the rating of the new review")
    private int rating;

    @CommandLine.Parameters(paramLabel = "<review_text>", description = "the review text of the new review")
    private String review_text;

    @CommandLine.Parameters(paramLabel = "<created_on>", description = "the date the new review was created on")
    private Date created_on;

    @Override
    public void run() {
        try {
            APP_CONTEXT.reviewService.createEntry(new ReviewEntity(rating, review_text, created_on, client_id, movie_id));
        } catch (BadEntityException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
}
