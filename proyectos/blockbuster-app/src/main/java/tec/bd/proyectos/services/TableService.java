package tec.bd.proyectos.services;

import java.sql.SQLException;
import java.util.List;

import tec.bd.proyectos.entities.Entity;
import tec.bd.proyectos.errors.BadEntityException;
import tec.bd.proyectos.errors.IDNotFoundException;
import tec.bd.proyectos.errors.ReadOnlyEntityException;
import tec.bd.proyectos.repository.Repository;

public class TableService<T extends Entity> {

    private Repository<T> repository;
    private final boolean isReadOnly;

    public TableService(Repository<T> repository) {
        this(repository, false);
    }

    public TableService(Repository<T> repository, boolean isReadOnly) {
        this.repository = repository;
        this.isReadOnly = isReadOnly;
    }

    public void createEntry(T entity) throws ReadOnlyEntityException, BadEntityException {
        if (isReadOnly) throw new ReadOnlyEntityException();
        try {
            repository.save(entity);
        } catch (SQLException e) {
            throw new BadEntityException(entity);
        }
    }

    public void dropEntry(int id) throws IDNotFoundException, ReadOnlyEntityException {
        if (isReadOnly) throw new ReadOnlyEntityException();
        try {
            repository.deleteByID(id);
        } catch (SQLException e) {
            throw new IDNotFoundException(id);
        }
    }

    public void updateEntry(T entity) throws IDNotFoundException, ReadOnlyEntityException {
        if (isReadOnly) throw new ReadOnlyEntityException();
        try {
            repository.update(entity);
        } catch (SQLException e) {
            throw new IDNotFoundException(entity.getID());
        }
    }

    public List<T> getAllEntires() {
        try {
            return repository.findAll();
        } catch (SQLException e) {
            // Should not happen ever.
            throw new RuntimeException(e);
        }
    }

    public T getEntry(int id) throws IDNotFoundException {
        try {
            return repository.findByID(id);
        } catch (SQLException|IndexOutOfBoundsException e) {
            throw new IDNotFoundException(id);
        }
    }
}
