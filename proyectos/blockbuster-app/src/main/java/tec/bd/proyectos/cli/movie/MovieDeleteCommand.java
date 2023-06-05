package tec.bd.proyectos.cli.movie;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "movd", description = "Deletes a movie")

public class MovieDeleteCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the movie to delete")
    private int id;

    @Override
    public void run() {
        try {
            APP_CONTEXT.movieService.dropEntry(id);
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
    
}
