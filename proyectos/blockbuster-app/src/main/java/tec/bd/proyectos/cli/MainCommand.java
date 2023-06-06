package tec.bd.proyectos.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import tec.bd.proyectos.cli.client.*;
import tec.bd.proyectos.cli.movie.*;
import tec.bd.proyectos.cli.rental.*;
import tec.bd.proyectos.cli.review.*;
import tec.bd.proyectos.cli.category.*;
import tec.bd.proyectos.cli.log.*;

@Command(
        name = "WeatherReport",
        subcommands = {
                HelpCommand.class,
                LogCommand.class,
                ClientCreateCommand.class,
                ClientDeleteCommand.class,
                ClientReturnCommand.class,
                ClientUpdateCommand.class,
                MovieCreateCommand.class,
                MovieDeleteCommand.class,
                MovieReturnCommand.class,
                MovieUpdateCommand.class,
                RentalCreateCommand.class,
                RentalDeleteCommand.class,
                RentalReturnCommand.class,
                RentalUpdateCommand.class,
                ReviewCreateCommand.class,
                ReviewDeleteCommand.class,
                ReviewReturnCommand.class,
                ReviewUpdateCommand.class,
                CategoryCreateCommand.class,
                CategoryDeleteCommand.class,
                CategoryReturnCommand.class,
                CategoryUpdateCommand.class
        },
        description = "Blockbuster CLI application")
public class MainCommand implements Runnable {

    @Override
    public void run() {

    }
}
