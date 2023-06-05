package tec.bd.proyectos.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.RentalEntity;

public class IRentalRepository extends Repository<RentalEntity> {

    private static final String FIND_ALL = "{ CALL rental_get_all() }";
    private static final String FIND_BY_ID = "{ CALL rental_get( ? ) }";
    private static final String SAVE = "{ CALL rental_insert( ?, ?, ? ) }";
    private static final String UPDATE = "{ CALL rental_update( ?, ?, ?, ? ) }";
    private static final String DELETE_BY_ID = "{ CALL rental_delete( ? ) }";

    public IRentalRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
    }

    @Override
    protected RentalEntity toEntity(ResultSet resultSet) throws SQLException {
        return new RentalEntity(resultSet.getInt("id"),
            resultSet.getDate("rental_date"),
            resultSet.getInt("client_id"),
            resultSet.getInt("movie_id"));
    }

    @Override
    public List<RentalEntity> findAll() throws SQLException {
        var statement = connect().prepareCall(FIND_ALL);
        var resultSet = query(statement);
        statement.close();
        return resultSet;
    }

    @Override
    public RentalEntity findByID(int id) throws SQLException {
        var statement = connect().prepareCall(FIND_BY_ID);
        statement.setInt(1, id);
        var resultSet = query(statement);
        statement.close();
        return resultSet.get(0);
    }

    @Override
    public void save(RentalEntity entity) throws SQLException {
        var statement = connect().prepareCall(SAVE);
        statement.setDate(1, new Date(entity.getRentalDate().getTime()));
        statement.setInt(2, entity.getClientID());
        statement.setInt(3, entity.getMovieID());
        update(statement);
        statement.close();
    }

    @Override
    public void update(RentalEntity entity) throws SQLException {
        var statement = connect().prepareCall(UPDATE);
        statement.setInt(1, entity.getID());
        statement.setDate(2, new Date(entity.getRentalDate().getTime()));
        statement.setInt(3, entity.getClientID());
        statement.setInt(4, entity.getMovieID());
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