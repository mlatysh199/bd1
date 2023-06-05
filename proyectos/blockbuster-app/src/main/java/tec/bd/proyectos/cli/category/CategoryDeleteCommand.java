package tec.bd.proyectos.cli.category;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "catd", description = "Deletes a category")

public class CategoryDeleteCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the category to delete")
    private int id;

    @Override
    public void run() {
        try {
            APP_CONTEXT.categoryService.dropEntry(id);
        } catch (IDNotFoundException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
    
}
