package tec.bd.proyectos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.MovieEntity;
import tec.bd.proyectos.entities.RentalEntity;
import tec.bd.proyectos.entities.ReviewEntity;

public class IMovieRepository extends StatementRepository<MovieEntity> {

    public IMovieRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource, new MovieEntity(), List.of(new RentalEntity(), new ReviewEntity()));
    }

    @Override
    protected MovieEntity toEntity(ResultSet resultSet) throws SQLException {
        return new MovieEntity(resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getDate("release_date"),
            resultSet.getInt("category_id"),
            resultSet.getInt("units_available"));
    }

    @Override
    protected PreparedStatement makeSaveStatement(MovieEntity entity, Connection connection) throws SQLException {
        var statement = connection.prepareStatement(builder.insert(entity));
        statement.setString(1, entity.getTitle());
        statement.setDate(2, new java.sql.Date(entity.getReleaseDate().getTime()));
        statement.setInt(3, entity.getCategoryID());
        statement.setInt(4, entity.getUnitsAvailable());
        return statement;
    }

    @Override
    protected PreparedStatement makeUpdateStatement(MovieEntity entity, Connection connection) throws SQLException {
        var statement = connection.prepareStatement(builder.update(entity));
        statement.setString(1, entity.getTitle());
        statement.setDate(2, new java.sql.Date(entity.getReleaseDate().getTime()));
        statement.setInt(3, entity.getCategoryID());
        statement.setInt(4, entity.getUnitsAvailable());
        statement.setInt(5, entity.getID());
        return statement;
    }  
}
