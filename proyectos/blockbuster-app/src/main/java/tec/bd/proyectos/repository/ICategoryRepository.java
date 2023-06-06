package tec.bd.proyectos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.CategoryEntity;
import tec.bd.proyectos.entities.MovieEntity;

public class ICategoryRepository extends StatementRepository<CategoryEntity> {

    public ICategoryRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource, new CategoryEntity(), List.of(new MovieEntity()));
    }

    @Override
    protected CategoryEntity toEntity(ResultSet resultSet) throws SQLException {
        return new CategoryEntity(resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("description"));
    }

    @Override
    protected PreparedStatement makeSaveStatement(CategoryEntity entity, Connection connection) throws SQLException {
        var statement = connection.prepareStatement(builder.insert(entity));
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        return statement;
    }

    @Override
    protected PreparedStatement makeUpdateStatement(CategoryEntity entity, Connection connection) throws SQLException {
        var statement = connection.prepareStatement(builder.update(entity));
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getDescription());
        statement.setInt(3, entity.getID());
        return statement;
    }
}
