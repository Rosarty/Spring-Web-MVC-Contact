package org.example.app.repository.impl.book;

import org.example.app.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean create(Book book) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "INSERT INTO Book (title, author, description, quota) " +
                "VALUES (:title, :author, :description, :quota)";
        MutationQuery query = session.createMutationQuery(hql);
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());
        query.setParameter("description", book.getDescription());
        query.setParameter("quota", book.getQuota());
        return query.executeUpdate() > 0;
    }

    @Override
    public Optional<List<Book>> fetchAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Book> list = session.createQuery("FROM Book",
                    Book.class).list();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Long id, Book book) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "UPDATE Book SET title = :title, " +
                    "author = :author, " +
                    "description = :description, " +
                    "quota = :quota " +
                    "WHERE id = :id";
            MutationQuery query = session.createMutationQuery(hql);
            query.setParameter("title", book.getTitle());
            query.setParameter("author", book.getAuthor());
            query.setParameter("description", book.getDescription());
            query.setParameter("quota", book.getQuota());
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Book book = session.byId(Book.class).load(id);
            session.remove(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Book> fetchById(Long id) {
        Optional<Book> optional;
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM Book b WHERE b.id = :id";
            Query<Book> query = session.createQuery(hql, Book.class);
            query.setParameter("id", id);
            optional = query.uniqueResultOptional();
            return optional;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
