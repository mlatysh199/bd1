package tec.bd.proyectos.entities;

import java.util.Date;

public class MovieEntity extends Entity {

    private String title;
    private Date release_date;
    private int category_id;
    private int units_available;

    public MovieEntity() {
    }

    public MovieEntity(int id, String title, Date release_date, int category_id, int units_available) {
        super(id);
        this.title = title;
        this.release_date = release_date;
        this.category_id = category_id;
        this.units_available = units_available;
    }

    public MovieEntity(String title, Date release_date, int category_id, int units_available) {
        this.title = title;
        this.release_date = release_date;
        this.category_id = category_id;
        this.units_available = units_available;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return release_date;
    }

    public int getCategoryID() {
        return category_id;
    }

    public int getUnitsAvailable() {
        return units_available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    public void setCategoryID(int category_id) {
        this.category_id = category_id;
    }

    public void setUnitsAvailable(int units_available) {
        this.units_available = units_available;
    }

    @Override
    public String basicSerialize() {
        return String.format("'%s', '%s', %d, %d", this.title, DATE_FORMATTER.format(this.release_date), this.category_id, this.units_available);
    }

    @Override
    public String advancedSerialize() {
        return String.format("title = '%s', release_date = '%s', category_id = %d, units_available = %d", this.title, DATE_FORMATTER.format(this.release_date), this.category_id, this.units_available);
    }

    @Override
    public String columnSerialize() {
        return "title, release_date, category_id, units_available";
    }
}
