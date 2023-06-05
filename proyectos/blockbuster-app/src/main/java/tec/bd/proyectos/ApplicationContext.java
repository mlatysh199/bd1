package tec.bd.proyectos;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import tec.bd.proyectos.entities.CategoryEntity;
import tec.bd.proyectos.entities.ClientEntity;
import tec.bd.proyectos.entities.LogEntity;
import tec.bd.proyectos.entities.MovieEntity;
import tec.bd.proyectos.entities.RentalEntity;
import tec.bd.proyectos.entities.ReviewEntity;
import tec.bd.proyectos.repository.ICategoryRepository;
import tec.bd.proyectos.repository.IClientRepository;
import tec.bd.proyectos.repository.ILogRepository;
import tec.bd.proyectos.repository.IMovieRepository;
import tec.bd.proyectos.repository.IRentalRepository;
import tec.bd.proyectos.repository.IReviewRepository;
import tec.bd.proyectos.services.TableService;

public class ApplicationContext {

    private ApplicationContext() {
    }

    public TableService<CategoryEntity> categoryService;
    public TableService<ClientEntity> clientService;
    public TableService<LogEntity> logService;
    public TableService<MovieEntity> movieService;
    public TableService<RentalEntity> rentalService;
    public TableService<ReviewEntity> reviewService;

    public static ApplicationContext init() {
        ApplicationContext applicationContext = new ApplicationContext();

        HikariDataSource source = createHikariDataSource();
        applicationContext.categoryService = new TableService<CategoryEntity>(new ICategoryRepository(source));
        applicationContext.clientService = new TableService<ClientEntity>(new IClientRepository(source));
        applicationContext.logService = new TableService<LogEntity>(new ILogRepository(source), true);
        applicationContext.movieService = new TableService<MovieEntity>(new IMovieRepository(source));
        applicationContext.rentalService = new TableService<RentalEntity>(new IRentalRepository(source));
        applicationContext.reviewService = new TableService<ReviewEntity>(new IReviewRepository(source));
        
        return applicationContext;
    }

    private static HikariDataSource createHikariDataSource() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/blockbuster");
        config.setUsername("blockbusterappuser");
        config.setPassword("blockbusterapppass");
        config.addDataSourceProperty("connectionTestQuery", "SELECT 1");
        config.addDataSourceProperty("maximumPoolSize", "4");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }
}
