package tec.bd.proyectos.cli.movie;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.IDNotFoundException;

@CommandLine.Command(name = "movr", description = "Returns a or all movies")
public class MovieReturnCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the movie to return", defaultValue = "return_all")
    private String id;

    @Override
    public void run() {
        if (id.equals("return_all")) {
            for (var movie : APP_CONTEXT.movieService.getAllEntires()) {
                System.out.println(movie.serialize());
            }
        } else {
            int id = Integer.parseInt(this.id);
            try {
                var movie = APP_CONTEXT.movieService.getEntry(id);
                System.out.println(movie.serialize());
            } catch (IDNotFoundException e) {
                System.out.println(new ExceptionReformatter(e).getFormattedMessage());
            }
        }
    }
    
}
