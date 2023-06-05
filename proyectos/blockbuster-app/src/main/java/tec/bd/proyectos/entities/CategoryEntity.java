package tec.bd.proyectos.entities;

public class CategoryEntity extends Entity {

    private String name;
    private String description;

    public CategoryEntity() {
    }

    public CategoryEntity(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public CategoryEntity(String name, String description) {
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
    public String basicSerialize() {
        return String.format("'%s', '%s'", this.name, this.description);
    }

    @Override
    public String advancedSerialize() {
        return String.format("name = '%s', description = '%s'", this.name, this.description);
    }

    @Override
    public String columnSerialize() {
        return "name, description";
    }

}
