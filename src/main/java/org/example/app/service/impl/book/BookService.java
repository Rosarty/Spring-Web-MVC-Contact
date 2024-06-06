package org.example.app.service.impl.book;

import org.example.app.entity.Book;
import org.example.app.service.BaseService;

import java.util.List;

public interface BookService extends BaseService<Book> {
    boolean create(Book book);
    List<Book> fetchAll();
    Book fetchById(Long id);
    boolean update(Long id, Book book);
    boolean delete(Long id);
}
