package tec.bd.proyectos.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.ReviewEntity;

public class IReviewRepository extends StatementRepository<ReviewEntity> {

    public IReviewRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource, new ReviewEntity(), List.of());
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
    protected PreparedStatement makeSaveStatement(ReviewEntity entity, Connection connection) throws SQLException {
        var statement = connection.prepareStatement(builder.insert(entity));
        statement.setInt(1, entity.getRating());
        statement.setString(2, entity.getReviewText());
        statement.setDate(3, new Date(entity.getCreatedOn().getTime()));
        statement.setInt(4, entity.getClientID());
        statement.setInt(5, entity.getMovieID());
        return statement;
    }

    @Override
    protected PreparedStatement makeUpdateStatement(ReviewEntity entity, Connection connection) throws SQLException {
        var statement = connection.prepareStatement(builder.update(entity));
        statement.setInt(1, entity.getRating());
        statement.setString(2, entity.getReviewText());
        statement.setDate(3, new Date(entity.getCreatedOn().getTime()));
        statement.setInt(4, entity.getClientID());
        statement.setInt(5, entity.getMovieID());
        statement.setInt(6, entity.getID());
        return statement;
    }
}
