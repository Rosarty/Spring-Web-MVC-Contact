package org.example.app.service.impl.book;

import jakarta.transaction.Transactional;
import org.example.app.entity.Book;
import org.example.app.repository.impl.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository repository;

    @Override
    @Transactional
    public boolean create(Book book) {
        return repository.create(book);
    }

    @Override
    @Transactional
    public List<Book> fetchAll() {
        return repository.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Book fetchById(Long id) {
        return repository.fetchById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean update(Long id, Book book) {
        return repository.update(id, book);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<Book> optional = repository.fetchById(id);
        if (optional.isPresent())
            return repository.delete(id);
        else return false;
    }
}
