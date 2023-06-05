package tec.bd.proyectos.entities;

import java.util.Date;

public class ReviewEntity extends Entity {

    private int rating;
    private String review_text;
    private Date created_on;
    private int client_id;
    private int movie_id;

    public ReviewEntity() {
    }

    public ReviewEntity(int id, int rating, String review_text, Date created_on, int client_id, int movie_id) {
        super(id);
        this.rating = rating;
        this.review_text = review_text;
        this.created_on = created_on;
        this.client_id = client_id;
        this.movie_id = movie_id;
    }

    public ReviewEntity(int rating, String review_text, Date created_on, int client_id, int movie_id) {
        this.rating = rating;
        this.review_text = review_text;
        this.created_on = created_on;
        this.client_id = client_id;
        this.movie_id = movie_id;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return review_text;
    }

    public Date getCreatedOn() {
        return created_on;
    }

    public int getClientID() {
        return client_id;
    }

    public int getMovieID() {
        return movie_id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewText(String review_text) {
        this.review_text = review_text;
    }

    public void setCreatedOn(Date created_on) {
        this.created_on = created_on;
    }

    public void setClientID(int client_id) {
        this.client_id = client_id;
    }

    public void setMovieID(int movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String basicSerialize() {
        return String.format("%d, '%s', '%s', %d, %d", this.rating, this.review_text, DATE_FORMATTER.format(this.created_on), this.client_id, this.movie_id);
    }

    @Override
    public String advancedSerialize() {
        return String.format("rating = %d, review_text = '%s', created_on = '%s', client_id = %d, movie_id = %d", this.rating, this.review_text, DATE_FORMATTER.format(this.created_on), this.client_id, this.movie_id);
    }

    @Override
    public String columnSerialize() {
        return "rating, review_text, created_on, client_id, movie_id";
    }
}
