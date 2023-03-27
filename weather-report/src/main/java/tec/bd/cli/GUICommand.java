package tec.bd.cli;

import picocli.CommandLine;
import tec.bd.ApplicationContext;
import tec.bd.gui.MainFrame;

@CommandLine.Command(name = "-gui", description = "Show gui menu.")
public class GUICommand implements Runnable {

    private static ApplicationContext APP_CONTEXT = ApplicationContext.init();

    // por favor, quiero que los actions ya empiecen a servir...

    @Override
    public void run() {
        System.out.println("GUI");

        new MainFrame(APP_CONTEXT);
    }
    
}
