package tec.bd.proyectos.repository;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.Entity;
import tec.bd.proyectos.repository.builders.GenericBuilder;

import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T extends Entity> {

    protected DataSource dataSource;
    protected GenericBuilder builder;

    protected Repository(HikariDataSource hikariDataSource, GenericBuilder builder) {
        this.dataSource = hikariDataSource;
        this.builder = builder;
    }

    protected Connection connect() throws SQLException {
        return this.dataSource.getConnection();
    }

    protected ResultSet rawQuery(String sqlQuery) throws SQLException {
        var connection = this.connect();
        Statement stmt = connection.createStatement();
        ResultSet resultSet;
        try {
            resultSet = stmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw e;
        } finally {
            stmt.close();
        }
        return resultSet;
    }

    protected List<T> query(String sqlQuery) throws SQLException {
        ResultSet resultSet = this.rawQuery(sqlQuery);
        return this.resultSetToEntityList(resultSet);
    }

    protected List<T> query(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        return this.resultSetToEntityList(resultSet);
    }

    protected int valueQuery(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    protected List<T> resultSetToEntityList(ResultSet resultSet) throws SQLException {
        var entities = new ArrayList<T>();
        while(resultSet.next()) {
            var entity = toEntity(resultSet);
            if (null != entity) entities.add(entity);
        }
        return entities;
    }

    protected void rawUpdate(String sqlQuery) throws SQLException {
        var connection = this.connect();
        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw e;
        } finally {
            stmt.close();
        }
    }

    protected void update(String sqlQuery) throws SQLException {
        this.rawUpdate(sqlQuery);
    }

    protected void update(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
    }

    protected abstract T toEntity(ResultSet resultSet) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract T findByID(int id) throws SQLException;

    public abstract void save(T entity) throws SQLException;

    public abstract void update(T entity) throws SQLException;

    public abstract void deleteByID(int id) throws SQLException;
}