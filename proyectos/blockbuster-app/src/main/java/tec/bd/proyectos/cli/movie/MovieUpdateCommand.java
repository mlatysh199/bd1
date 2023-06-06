package tec.bd.proyectos.cli.movie;

import java.util.Date;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.MovieEntity;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "movu", description = "Updates a movie")
public class MovieUpdateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the movie to update")
    private int id;

    @CommandLine.Parameters(paramLabel = "<title>", description = "the new title of the movie to update")
    private String title;

    @CommandLine.Parameters(paramLabel = "<release_date>", description = "the new release date of the movie to update")
    private Date release_date;

    @CommandLine.Parameters(paramLabel = "<category_id>", description = "the new category id of the movie to update")
    private int category_id;

    @CommandLine.Parameters(paramLabel = "<units_available>", description = "the new number of units available of the movie to update")
    private int units_available;

    @Override
    public void run() {
        try {
            APP_CONTEXT.movieService.updateEntry(new MovieEntity(id, title, release_date, category_id, units_available));
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            System.out.println(new ExceptionReformatter(e).getFormattedMessage());
        }
    }
    
}
