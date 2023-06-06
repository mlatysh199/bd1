package tec.bd.proyectos.repository.builders;

import tec.bd.proyectos.entities.Entity;

public class StatementBuilder implements GenericBuilder {
    public String get_all(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(entity.getSQLName());
        return sb.toString();
    }

    public String get(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(entity.getSQLName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public String insert(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(entity.getSQLName());
        sb.append(" (");
        sb.append(entity.serializeParams());
        sb.append(") VALUES (");
        for (int i = 0; i < entity.getParamAmount(); i++) {
            sb.append("?");
            if (i < entity.getParamAmount() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public String update(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(entity.getSQLName());
        sb.append(" SET ");
        for (int i = 0; i < entity.getParamAmount(); i++) {
            sb.append(entity.getParams().get(i));
            sb.append(" = ?");
            if (i < entity.getParamAmount() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public String delete(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(entity.getSQLName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    public String contains(Entity entity, Entity dependentEntity) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) FROM ");
        sb.append(dependentEntity.getSQLName());
        sb.append(" WHERE ");
        sb.append(entity.getSQLName());
        sb.append("_id = ?");
        return sb.toString();
    }

    public String contains(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) FROM ");
        sb.append(entity.getSQLName());
        sb.append(" WHERE id = ?");
        return sb.toString();
    }
}
