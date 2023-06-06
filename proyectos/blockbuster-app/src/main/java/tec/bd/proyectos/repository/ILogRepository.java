package tec.bd.proyectos.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.LogEntity;

public class ILogRepository extends ProcedureRepository<LogEntity> {

    public ILogRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource, new LogEntity());
    }

    @Override
    protected LogEntity toEntity(ResultSet resultSet) throws SQLException {
        return new LogEntity(resultSet.getInt("id"),
            resultSet.getString("table_name"),
            resultSet.getDate("created_on"),
            resultSet.getString("entry_text"));
    }

    @Override
    protected CallableStatement makeSaveCallStatement(LogEntity entity) throws SQLException {
        var statement = connect().prepareCall(builder.insert(entity));
        statement.setString(1, entity.getTableName());
        statement.setDate(2, new Date(entity.getCreatedOn().getTime()));
        statement.setString(3, entity.getEntryText());
        return statement;
    }

    @Override
    protected CallableStatement makeUpdateCallStatement(LogEntity entity) throws SQLException {
        var statement = connect().prepareCall(builder.update(entity));
        statement.setInt(1, entity.getID());
        statement.setString(2, entity.getTableName());
        statement.setDate(3, new Date(entity.getCreatedOn().getTime()));
        statement.setString(4, entity.getEntryText());
        return statement;
    }
}
