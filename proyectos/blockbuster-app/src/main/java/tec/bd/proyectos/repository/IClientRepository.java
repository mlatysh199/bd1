package tec.bd.proyectos.repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.ClientEntity;

public class IClientRepository extends ProcedureRepository<ClientEntity> {

    public IClientRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource, new ClientEntity());
    }

    @Override
    protected ClientEntity toEntity(ResultSet resultSet) throws SQLException {
        return new ClientEntity(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("lastname"),
            resultSet.getString("email"),
            resultSet.getString("phone_number"));
    }

    @Override
    protected CallableStatement makeSaveCallStatement(ClientEntity entity) throws SQLException {
        var statement = connect().prepareCall(builder.insert(entity));
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getLastname());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPhoneNumber());
        return statement;
    }

    @Override
    protected CallableStatement makeUpdateCallStatement(ClientEntity entity) throws SQLException {
        var statement = connect().prepareCall(builder.update(entity));
        statement.setInt(1, entity.getID());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getLastname());
        statement.setString(4, entity.getEmail());
        statement.setString(5, entity.getPhoneNumber());
        return statement;
    }
}
