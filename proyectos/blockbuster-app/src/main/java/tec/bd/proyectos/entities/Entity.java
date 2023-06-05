package tec.bd.proyectos.entities;

import java.text.SimpleDateFormat;

public abstract class Entity {
    private int id;
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/YYYY");

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public abstract String basicSerialize();
    
    public abstract String advancedSerialize();

    public abstract String columnSerialize();

    public String serializeID() {
        return String.format("id = %d", this.id);
    }
}
