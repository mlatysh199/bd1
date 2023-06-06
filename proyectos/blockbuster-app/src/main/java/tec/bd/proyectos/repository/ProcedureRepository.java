package tec.bd.proyectos.repository;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.Entity;
import tec.bd.proyectos.repository.builders.ProcedureBuilder;

public abstract class ProcedureRepository<T extends Entity> extends Repository<T> {

    private T baseEntity;

    protected ProcedureRepository(HikariDataSource hikariDataSource, T baseEntity) {
        super(hikariDataSource, new ProcedureBuilder());
        this.baseEntity = baseEntity;
    }

    @Override
    public List<T> findAll() throws SQLException {
        var statement = connect().prepareCall(builder.get_all(baseEntity));
        var resultSet = query(statement);
        statement.close();
        return resultSet;
    }

    @Override
    public T findByID(int id) throws SQLException {
        var statement = connect().prepareCall(builder.get(baseEntity));
        statement.setInt(1, id);
        var resultSet = query(statement);
        statement.close();
        return resultSet.get(0);
    }

    @Override
    public void save(T entity) throws SQLException {
        var statement = makeSaveCallStatement(entity);
        try {
            update(statement);
        } catch (SQLException e) {
            throw e;
        } finally {
            statement.close();
        }
    }

    protected abstract CallableStatement makeSaveCallStatement(T entity) throws SQLException;

    @Override
    public void update(T entity) throws SQLException {
        var statement = makeUpdateCallStatement(entity);
        try {
            update(statement);
        } catch (SQLException e) {
            throw e;
        } finally {
            statement.close();
        }
    }

    protected abstract CallableStatement makeUpdateCallStatement(T entity) throws SQLException;

    @Override
    public void deleteByID(int id) throws SQLException {
        var statement = connect().prepareCall(builder.delete(baseEntity));
        statement.setInt(1, id);
        try {
            update(statement);
        } catch (SQLException e) {
            throw e;
        } finally {
            statement.close();
        }
    }
    
}
