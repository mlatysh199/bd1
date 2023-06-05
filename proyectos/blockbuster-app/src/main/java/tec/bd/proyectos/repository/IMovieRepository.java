package tec.bd.proyectos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.MovieEntity;

public class IMovieRepository extends Repository<MovieEntity> {

    public IMovieRepository(HikariDataSource hikariDataSource) {
        super(hikariDataSource);
    }

    @Override
    protected MovieEntity toEntity(ResultSet resultSet) throws SQLException {
        return new MovieEntity(resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getDate("release_date"),
            resultSet.getInt("category_id"),
            resultSet.getInt("units_available"));
    }

    @Override
    public List<MovieEntity> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public MovieEntity findByID(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
    }

    @Override
    public void save(MovieEntity entity) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void update(MovieEntity entity) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void deleteByID(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByID'");
    }   
}
