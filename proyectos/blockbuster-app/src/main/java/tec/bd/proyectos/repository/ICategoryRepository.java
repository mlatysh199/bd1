package tec.bd.proyectos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.CategoryEntity;

public class ICategoryRepository extends Repository<CategoryEntity> {

    private static final String FIND_ALL = "{ CALL category_get_all() }";
    private static final String FIND_BY_ID = "{ CALL category_get( ? ) }";
    private static final String SAVE = "{ CALL category_insert( ?, ? ) }";
    private static final String UPDATE = "{ CALL category_update( ?, ?, ? ) }";
    private static final String DELETE_BY_ID = "{ CALL category_delete( ? ) }";

    public ICategoryRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
    }

    @Override
    protected CategoryEntity toEntity(ResultSet resultSet) throws SQLException {
        return new CategoryEntity(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("description"));
    }

    @Override
    public List<CategoryEntity> findAll() throws SQLException {
        var statement = connect().prepareCall(FIND_ALL);
        var resultSet = query(statement);
        statement.close();
        return resultSet;
    }

    @Override
    public CategoryEntity findByID(int id) throws SQLException {
        var statement = connect().prepareCall(FIND_BY_ID);
        statement.setInt(1, id);
        var resultSet = query(statement);
        statement.close();
        return resultSet.get(0);
    }

    @Override
    public void save(CategoryEntity entity) throws SQLException {
        var statement = connect().prepareCall(SAVE);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        update(statement);
        statement.close();
    }

    @Override
    public void update(CategoryEntity entity) throws SQLException {
        var statement = connect().prepareCall(UPDATE);
        statement.setInt(1, entity.getID());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getDescription());
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
