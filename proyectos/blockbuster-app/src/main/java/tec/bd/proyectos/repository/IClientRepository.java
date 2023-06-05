package tec.bd.proyectos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.ClientEntity;

public class IClientRepository extends Repository<ClientEntity> {

    private static final String FIND_ALL = "{ CALL client_get_all() }";
    private static final String FIND_BY_ID = "{ CALL client_get( ? ) }";
    private static final String SAVE = "{ CALL client_insert( ?, ?, ?, ? ) }";
    private static final String UPDATE = "{ CALL client_update( ?, ?, ?, ?, ? ) }";
    private static final String DELETE_BY_ID = "{ CALL client_delete( ? ) }";

    public IClientRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
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
    public List<ClientEntity> findAll() throws SQLException {
        var statement = connect().prepareCall(FIND_ALL);
        var resultSet = query(statement);
        statement.close();
        return resultSet;
    }

    @Override
    public ClientEntity findByID(int id) throws SQLException {
        var statement = connect().prepareCall(FIND_BY_ID);
        statement.setInt(1, id);
        var resultSet = query(statement);
        statement.close();
        return resultSet.get(0);
    }

    @Override
    public void save(ClientEntity entity) throws SQLException {
        var statement = connect().prepareCall(SAVE);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getLastname());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPhoneNumber());
        update(statement);
        statement.close();
    }

    @Override
    public void update(ClientEntity entity) throws SQLException {
        var statement = connect().prepareCall(UPDATE);
        statement.setInt(1, entity.getID());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getLastname());
        statement.setString(4, entity.getEmail());
        statement.setString(5, entity.getPhoneNumber());
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
