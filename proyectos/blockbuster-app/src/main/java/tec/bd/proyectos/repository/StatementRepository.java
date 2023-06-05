package tec.bd.proyectos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.Entity;
import tec.bd.proyectos.repository.builders.StatementBuilder;

public abstract class StatementRepository<T extends Entity> extends Repository<T> {

    private T baseEntity;
    private List<Entity> dependets;

    protected StatementRepository(HikariDataSource hikariDataSource, T baseEntity, List<Entity> dependets) {
        super(hikariDataSource, new StatementBuilder());
        this.baseEntity = baseEntity;
        this.dependets = dependets;
    }

    @Override
    public List<T> findAll() throws SQLException {
        var statement = connect().prepareStatement(builder.get_all(baseEntity));
        var resultSet = query(statement);
        statement.close();
        return resultSet;
    }

    @Override
    public T findByID(int id) throws SQLException {
        var statement = connect().prepareStatement(builder.get(baseEntity));
        statement.setInt(1, id);
        var resultSet = query(statement);
        statement.close();
        return resultSet.get(0);
    }

    @Override
    public void save(T entity) throws SQLException {
        try (var connection = connect()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Savepoint savepoint = connection.setSavepoint("save");
            try {
                var statement = makeSaveStatement(entity, connection);
                try {
                    update(statement);
                } catch (SQLException e) {
                    connection.rollback(savepoint);
                    throw e;
                } finally {
                    statement.close();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback(savepoint);
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    protected abstract PreparedStatement makeSaveStatement(T entity, Connection connection) throws SQLException;

    @Override
    public void update(T entity) throws SQLException {
        try (var connection = connect()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Savepoint savepoint = connection.setSavepoint("update");
            try {
                var statement = makeUpdateStatement(entity, connection);
                try {
                    var statement2 = connection.prepareStatement(builder.contains(baseEntity));
                    statement2.setInt(1, entity.getID());
                    int amount = valueQuery(statement2);
                    statement2.close();
                    if (amount == 0) {
                        throw new SQLException("Cannot update " + baseEntity.getClass().getSimpleName() + " with id " + entity.getID() + " because it does not exist");
                    }
                    update(statement);
                } catch (SQLException e) {
                    connection.rollback(savepoint);
                    throw e;
                } finally {
                    statement.close();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback(savepoint);
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }
    }  

    protected abstract PreparedStatement makeUpdateStatement(T entity, Connection connection) throws SQLException;

    @Override
    public void deleteByID(int id) throws SQLException {
        try (var connection = connect()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            Savepoint savepoint = connection.setSavepoint("deleteByID");
            try {
                var statement = connection.prepareStatement(builder.delete(baseEntity));
                statement.setInt(1, id);
                try {
                    for (var entity : dependets) {
                        var statement2 = connection.prepareStatement(builder.contains(baseEntity, entity));
                        statement2.setInt(1, id);
                        int amount = valueQuery(statement2);
                        statement2.close();
                        if (amount > 0) {
                            throw new SQLException("Cannot delete " + baseEntity.getClass().getSimpleName() + " with id " + id + " because it has " + amount + " " + entity.getClass().getSimpleName() + "s");
                        }
                    }
                    var statement2 = connection.prepareStatement(builder.contains(baseEntity));
                    statement2.setInt(1, id);
                    int amount = valueQuery(statement2);
                    statement2.close();
                    if (amount == 0) {
                        throw new SQLException("Cannot delete " + baseEntity.getClass().getSimpleName() + " with id " + id + " because it does not exist");
                    }
                    update(statement);
                } catch (SQLException e) {
                    connection.rollback(savepoint);
                    throw e;
                } finally {
                    statement.close();
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback(savepoint);
                throw e;
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
