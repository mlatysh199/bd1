package tec.bd.proyectos.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.RentalEntity;

public class IRentalRepository extends ProcedureRepository<RentalEntity> {

    public IRentalRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource, new RentalEntity());
    }

    @Override
    protected RentalEntity toEntity(ResultSet resultSet) throws SQLException {
        return new RentalEntity(resultSet.getInt("id"),
            resultSet.getDate("rental_date"),
            resultSet.getInt("client_id"),
            resultSet.getInt("movie_id"));
    }

    @Override
    protected CallableStatement makeSaveCallStatement(RentalEntity entity) throws SQLException {
        var statement = connect().prepareCall(builder.insert(entity));
        statement.setDate(1, new Date(entity.getRentalDate().getTime()));
        statement.setInt(2, entity.getClientID());
        statement.setInt(3, entity.getMovieID());
        return statement;
    }

    @Override
    protected CallableStatement makeUpdateCallStatement(RentalEntity entity) throws SQLException {
        var statement = connect().prepareCall(builder.update(entity));
        statement.setInt(1, entity.getID());
        statement.setDate(2, new Date(entity.getRentalDate().getTime()));
        statement.setInt(3, entity.getClientID());
        statement.setInt(4, entity.getMovieID());
        return statement;
    }
}