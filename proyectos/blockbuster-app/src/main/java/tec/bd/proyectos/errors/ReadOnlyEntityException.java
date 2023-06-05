package tec.bd.proyectos.errors;

public class ReadOnlyEntityException extends Exception {
    public ReadOnlyEntityException() {
        super("Can't modify read-only entity");
    }
}
