package tec.bd.proyectos.entities;

import java.util.Date;

public class LogEntity extends Entity {

    private String table_name;
    private Date created_on;
    private String entry_text;

    public LogEntity() {
    }

    public LogEntity(String table_name, Date created_on, String entry_text) {
        this.table_name = table_name;
        this.created_on = created_on;
        this.entry_text = entry_text;
    }

    public LogEntity(int id, String table_name, Date created_on, String entry_text) {
        super(id);
        this.table_name = table_name;
        this.created_on = created_on;
        this.entry_text = entry_text;
    }

    public String getTableName() {
        return table_name;
    }

    public void setTableName(String table_name) {
        this.table_name = table_name;
    }

    public Date getCreatedOn() {
        return created_on;
    }

    public void setCreatedOn(Date created_on) {
        this.created_on = created_on;
    }

    public String getEntryText() {
        return entry_text;
    }

    public void setEntryText(String entry_text) {
        this.entry_text = entry_text;
    }

    @Override
    public String basicSerialize() {
        return String.format("'%s', '%s', '%s'", this.table_name, DATE_FORMATTER.format(this.created_on), this.entry_text);
    }

    @Override
    public String advancedSerialize() {
        return String.format("table_name = '%s', created_on = '%s', entry_text = '%s'", this.table_name, DATE_FORMATTER.format(this.created_on), this.entry_text);
    }

    @Override
    public String columnSerialize() {
        return "table_name, created_on, entry_text";
    }

}
