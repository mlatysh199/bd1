package tec.bd.proyectos.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.LogEntity;

public class ILogRepository extends Repository<LogEntity> {

    private static final String FIND_ALL = "{ CALL blockbuster_log_get_all() }";
    private static final String FIND_BY_ID = "{ CALL blockbuster_log_get( ? ) }";
    private static final String SAVE = "{ CALL blockbuster_log_insert( ?, ?, ? ) }";
    private static final String UPDATE = "{ CALL blockbuster_log_update( ?, ?, ?, ? ) }";
    private static final String DELETE_BY_ID = "{ CALL blockbuster_log_delete( ? ) }";

    public ILogRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
    }

    @Override
    protected LogEntity toEntity(ResultSet resultSet) throws SQLException {
        return new LogEntity(resultSet.getInt("id"),
            resultSet.getString("table_name"),
            resultSet.getDate("created_on"),
            resultSet.getString("entry_text"));
    }

    @Override
    public List<LogEntity> findAll() throws SQLException {
        var statement = connect().prepareCall(FIND_ALL);
        var resultSet = query(statement);
        statement.close();
        return resultSet;
    }

    @Override
    public LogEntity findByID(int id) throws SQLException {
        var statement = connect().prepareCall(FIND_BY_ID);
        statement.setInt(1, id);
        var resultSet = query(statement);
        statement.close();
        return resultSet.get(0);
    }

    @Override
    public void save(LogEntity entity) throws SQLException {
        var statement = connect().prepareCall(SAVE);
        statement.setString(1, entity.getTableName());
        statement.setDate(2, new Date(entity.getCreatedOn().getTime()));
        statement.setString(3, entity.getEntryText());
        update(statement);
        statement.close();
    }

    @Override
    public void update(LogEntity entity) throws SQLException {
        var statement = connect().prepareCall(UPDATE);
        statement.setInt(1, entity.getID());
        statement.setString(2, entity.getTableName());
        statement.setDate(3, new Date(entity.getCreatedOn().getTime()));
        statement.setString(4, entity.getEntryText());
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
