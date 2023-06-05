package tec.bd.proyectos.cli.category;

import picocli.CommandLine;
import tec.bd.proyectos.ApplicationContext;
import tec.bd.proyectos.errors.IDNotFoundException;

@CommandLine.Command(name = "catr", description = "Returns a or all categories")
public class CategoryReturnCommand implements Runnable {
    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    @CommandLine.Parameters(paramLabel = "<id>", description = "the id of the category to return", defaultValue = "return_all")
    private String id;

    @Override
    public void run() {
        if (id.equals("return_all")) {
            for (var category : APP_CONTEXT.categoryService.getAllEntires()) {
                System.out.println(category.serialize());
            }
        } else {
            int id = Integer.parseInt(this.id);
            try {
                var category = APP_CONTEXT.categoryService.getEntry(id);
                System.out.println(category.serialize());
            } catch (IDNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
}
