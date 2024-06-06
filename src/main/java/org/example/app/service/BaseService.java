package org.example.app.service;

import java.util.List;

public interface BaseService<T> {
    boolean create(T obj);
    List<T> fetchAll();
    T fetchById(Long id);
    boolean update(Long id, T obj);
    boolean delete(Long id);
}
