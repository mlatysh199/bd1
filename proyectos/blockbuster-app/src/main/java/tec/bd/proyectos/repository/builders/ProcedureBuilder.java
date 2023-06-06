package tec.bd.proyectos.repository.builders;

import tec.bd.proyectos.entities.Entity;

public class ProcedureBuilder implements GenericBuilder {
    public String get_all(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ CALL ");
        sb.append(entity.getSQLName());
        sb.append("_get_all() }");
        return sb.toString();
    }

    public String get(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ CALL ");
        sb.append(entity.getSQLName());
        sb.append("_get(?) }");
        return sb.toString();
    }

    public String insert(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ CALL ");
        sb.append(entity.getSQLName());
        sb.append("_insert(");
        for (int i = 0; i < entity.getParamAmount(); i++) {
            sb.append("?");
            if (i < entity.getParamAmount() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") }");
        return sb.toString();
    }

    public String update(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ CALL ");
        sb.append(entity.getSQLName());
        sb.append("_update(?, ");
        for (int i = 0; i < entity.getParamAmount(); i++) {
            sb.append("?");
            if (i < entity.getParamAmount() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") }");
        return sb.toString();
    }

    public String delete(Entity entity) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ CALL ");
        sb.append(entity.getSQLName());
        sb.append("_delete(?) }");
        return sb.toString();
    }

    @Override
    public String contains(Entity entity, Entity dependentEntity) {
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public String contains(Entity entity) {
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }
}
