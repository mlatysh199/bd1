package tec.bd.proyectos.entities;

import java.util.Date;

public class RentalEntity extends Entity {

    private Date rental_date;
    private int client_id;
    private int movie_id;

    public RentalEntity() {

    }

    public RentalEntity(int id, Date rental_date, int client_id, int movie_id) {
        super(id);
        this.rental_date = rental_date;
        this.client_id = client_id;
        this.movie_id = movie_id;
    }

    public RentalEntity(Date rental_date, int client_id, int movie_id) {
        this.rental_date = rental_date;
        this.client_id = client_id;
        this.movie_id = movie_id;
    }

    public Date getRentalDate() {
        return rental_date;
    }

    public int getClientID() {
        return client_id;
    }

    public int getMovieID() {
        return movie_id;
    }

    public void setRentalDate(Date rental_date) {
        this.rental_date = rental_date;
    }

    public void setClientID(int client_id) {
        this.client_id = client_id;
    }

    public void setMovieID(int movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String basicSerialize() {
        return String.format("'%s', %d, %d", this.rental_date, this.client_id, this.movie_id);
    }

    @Override
    public String advancedSerialize() {
        return String.format("rental_date = '%s', client_id = %d, movie_id = %d", this.rental_date, this.client_id, this.movie_id);
    }

    @Override
    public String columnSerialize() {
        return "rental_date, client_id, movie_id";
    }
}
