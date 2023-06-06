package tec.bd.proyectos.cli.category;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.CategoryEntity;
import tec.bd.proyectos.errors.BadEntityException;
import tec.bd.proyectos.errors.ExceptionReformatter;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "catc", description = "Creates a category")
public class CategoryCreateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<name>", description = "the name of the new category")
    private String name;

    @CommandLine.Parameters(paramLabel = "<description>", description = "the description of the new category", defaultValue = "")
    private String description;

    @Override
    public void run() {
        try {
            APP_CONTEXT.categoryService.createEntry(new CategoryEntity(name, description));
        } catch (BadEntityException|ReadOnlyEntityException e) {
            System.out.println(new ExceptionReformatter(e).getFormattedMessage());
        }
    }
}
