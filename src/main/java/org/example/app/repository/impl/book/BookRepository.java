package org.example.app.repository.impl.book;

import org.example.app.entity.Book;
import org.example.app.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends BaseRepository<Book> {
    boolean create(Book book);
    Optional<List<Book>> fetchAll();
    Optional<Book> fetchById(Long id);
    boolean update(Long id, Book book);
    boolean delete(Long id);
}
