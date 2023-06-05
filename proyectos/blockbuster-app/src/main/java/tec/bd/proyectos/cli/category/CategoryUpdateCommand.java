package tec.bd.proyectos.cli.category;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.entities.CategoryEntity;
import tec.bd.proyectos.errors.BadEntityException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;

@CommandLine.Command(name = "catu", description = "Updates a category")
public class CategoryUpdateCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the category to update")
    private int id;

    @CommandLine.Parameters(paramLabel = "<name>", description = "the new name of the category to update")
    private String name;

    @CommandLine.Parameters(paramLabel = "<description>", description = "the new description of the category to update")
    private String description;

    @Override
    public void run() {
        try {
            APP_CONTEXT.categoryService.updateEntry(new CategoryEntity(id, name, description));
        } catch (BadEntityException|ReadOnlyEntityException e) {
            e.printStackTrace();
        }
    }
    
}
