package tec.bd.proyectos.errors;

public class IDNotFoundException extends Exception {
    public IDNotFoundException(int id) {
        super("ID " + id + " not found in consulted table");
    }
}
