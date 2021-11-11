package edu.school21.sockets.repositories;

import java.util.List;

public interface CrudRepository<T> {
    T findById(Long id);

    List<T> findAll();

    void save(T entry);

    void update(T entry);

    void delete(Long id);
}
