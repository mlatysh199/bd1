package tec.bd.proyectos.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.ReviewEntity;

public class IReviewRepository extends Repository<ReviewEntity> {

    private static final String FIND_ALL = "{ CALL review_get_all() }";
    private static final String FIND_BY_ID = "{ CALL review_get( ? ) }";
    private static final String SAVE = "{ CALL review_insert( ?, ?, ?, ?, ? ) }";
    private static final String UPDATE = "{ CALL review_update( ?, ?, ?, ?, ?, ? ) }";
    private static final String DELETE_BY_ID = "{ CALL review_delete( ? ) }";

    public IReviewRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
    }

    @Override
    protected ReviewEntity toEntity(ResultSet resultSet) throws SQLException {
        return new ReviewEntity(resultSet.getInt("id"),
            resultSet.getInt("rating"),
            resultSet.getString("review_text"),
            resultSet.getDate("created_on"),
            resultSet.getInt("client_id"),
            resultSet.getInt("movie_id"));
    }

    @Override
    public List<ReviewEntity> findAll() throws SQLException {
        var statement = connect().prepareCall(FIND_ALL);
        var resultSet = query(statement);
        statement.close();
        return resultSet;
    }

    @Override
    public ReviewEntity findByID(int id) throws SQLException {
        var statement = connect().prepareCall(FIND_BY_ID);
        statement.setInt(1, id);
        var resultSet = query(statement);
        statement.close();
        return resultSet.get(0);
    }

    @Override
    public void save(ReviewEntity entity) throws SQLException {
        var statement = connect().prepareCall(SAVE);
        statement.setInt(1, entity.getRating());
        statement.setString(2, entity.getReviewText());
        statement.setDate(3, new Date(entity.getCreatedOn().getTime()));
        statement.setInt(4, entity.getClientID());
        statement.setInt(5, entity.getMovieID());
        update(statement);
        statement.close();
    }

    @Override
    public void update(ReviewEntity entity) throws SQLException {
        var statement = connect().prepareCall(UPDATE);
        statement.setInt(1, entity.getID());
        statement.setInt(2, entity.getRating());
        statement.setString(3, entity.getReviewText());
        statement.setDate(4, new Date(entity.getCreatedOn().getTime()));
        statement.setInt(5, entity.getClientID());
        statement.setInt(6, entity.getMovieID());
        update(statement);
        statement.close();
    }

    @Override
    public void deleteByID(int id) throws SQLException {
        var statement = connect().prepareCall(DELETE_BY_ID);
        statement.setInt(1, id);
        update(statement);
        statement.close();
    }
}
