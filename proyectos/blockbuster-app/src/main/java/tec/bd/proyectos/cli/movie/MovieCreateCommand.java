package tec.bd.proyectos.cli.movie;

import java.util.Date;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.MovieEntity;
import tec.bd.proyectos.errors.BadEntityException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "movc", description = "Creates a movie")
public class MovieCreateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<title>", description = "the title of the new movie")
    private String title;

    @CommandLine.Parameters(paramLabel = "<release_date>", description = "the release date of the new movie")
    private Date release_date;

    @CommandLine.Parameters(paramLabel = "<category_id>", description = "the category id of the movie to update")
    private int category_id;

    @CommandLine.Parameters(paramLabel = "<units_available>", description = "the number units available of the new movie")
    private int units_available;

    @Override
    public void run() {
        try {
            APP_CONTEXT.movieService.createEntry(new MovieEntity(title, release_date, category_id, units_available));
        } catch (BadEntityException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
}
