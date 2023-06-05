package tec.bd.proyectos.repository.builders;

import tec.bd.proyectos.entities.Entity;

public interface GenericBuilder {
    public String get_all(Entity entity);

    public String get(Entity entity);

    public String insert(Entity entity);

    public String update(Entity entity);

    public String delete(Entity entity);

    public String contains(Entity entity, Entity dependentEntity);

    public String contains(Entity entity);
}
