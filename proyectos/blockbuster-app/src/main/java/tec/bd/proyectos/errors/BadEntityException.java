package tec.bd.proyectos.errors;

import tec.bd.proyectos.entities.Entity;

public class BadEntityException extends Exception {
    public BadEntityException(Entity entity) {
        super("Received bad entity: " + entity.serialize());
    }
}
