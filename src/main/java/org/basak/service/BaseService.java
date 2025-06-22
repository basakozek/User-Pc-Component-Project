package org.basak.service;
import org.basak.repository.RepositoryManager;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> {

    protected RepositoryManager<T, ID> repository;

    // Constructor injection for repository
    public BaseService(RepositoryManager<T, ID> repository) {
        this.repository = repository;
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public Iterable<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    @Transactional
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Transactional
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Transactional
    public T update(T entity) {
        return repository.update(entity);
    }
}

