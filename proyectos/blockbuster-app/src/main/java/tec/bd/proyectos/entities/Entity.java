package tec.bd.proyectos.entities;

import java.text.SimpleDateFormat;
import java.util.List;

public abstract class Entity {
    private int id;
    private int paramAmount;
    private String sqlName;
    private List<String> params;
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public Entity(int paramAmount, String sqlName, List<String> params) {
        this.paramAmount = paramAmount;
        this.sqlName = sqlName;
        this.params = params;
    }

    public Entity(int id, int paramAmount, String sqlName, List<String> params) {
        this.id = id;
        this.paramAmount = paramAmount;
        this.sqlName = sqlName;
        this.params = params;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getParamAmount() {
        return paramAmount;
    }

    public void setParamAmount(int paramAmount) {
        this.paramAmount = paramAmount;
    }

    public String getSQLName() {
        return sqlName;
    }

    public void setSQLName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String serializeID() {
        return String.format("id = %d", this.id);
    }

    public abstract String serialize();

    public abstract String basicSerialize();

    public abstract String advancedSerialize();

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = List.copyOf(params);
    }

    public String serializeParams() {
        return getParams().stream().reduce((a, b) -> a + ", " + b).get();
    }
}
