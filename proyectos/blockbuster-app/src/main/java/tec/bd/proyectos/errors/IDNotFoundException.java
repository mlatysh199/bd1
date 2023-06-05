package tec.bd.proyectos.errors;

public class IDNotFoundException extends Exception {
    public IDNotFoundException(int id) {
        super("ID " + id + " not found or is invalid for given operation in consulted table");
    }
}
