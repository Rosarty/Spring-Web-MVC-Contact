package org.example.app.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    boolean create(T obj);
    Optional<List<T>> fetchAll();
    Optional<T> fetchById(Long id);
    boolean update(Long id, T obj);
    boolean delete(Long id);
}