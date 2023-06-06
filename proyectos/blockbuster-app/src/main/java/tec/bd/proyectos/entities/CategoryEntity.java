package tec.bd.proyectos.entities;

import java.util.List;

public class CategoryEntity extends Entity {

    private String name;
    private String description;

    public CategoryEntity() {
        super(2, "category", List.of("name", "description"));
    }

    public CategoryEntity(int id, String name, String description) {
        super(id, 2, "category", List.of("name", "description"));
        this.name = name;
        this.description = description;
    }

    public CategoryEntity(String name, String description) {
        super(2, "category", List.of("name", "description"));
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String serialize() {
        return serializeID() + ", " + advancedSerialize();
    }

    @Override
    public String basicSerialize() {
        return String.format("'%s', '%s'", this.name, this.description);
    }

    @Override
    public String advancedSerialize() {
        return String.format("name = '%s', description = '%s'", this.name, this.description);
    }
}
