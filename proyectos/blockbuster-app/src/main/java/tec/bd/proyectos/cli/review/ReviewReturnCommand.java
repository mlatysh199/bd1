package tec.bd.proyectos.cli.review;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.IDNotFoundException;

@CommandLine.Command(name = "revr", description = "Returns a or all reviews")
public class ReviewReturnCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the review to return", defaultValue = "return_all")
    private String id;

    @Override
    public void run() {
        if (id.equals("return_all")) {
            for (var review : APP_CONTEXT.reviewService.getAllEntires()) {
                System.out.println(review.serializeID() + " " + review.advancedSerialize());
            }
        } else {
            int id = Integer.parseInt(this.id);
            try {
                var review = APP_CONTEXT.reviewService.getEntry(id);
                System.out.println(review.serializeID() + " " + review.advancedSerialize());
            } catch (IDNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
}
