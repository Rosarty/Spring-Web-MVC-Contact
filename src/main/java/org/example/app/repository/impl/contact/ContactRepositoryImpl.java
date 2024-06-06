package org.example.app.repository.impl.contact;

import org.example.app.entity.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean create(Contact contact) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.persist(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<List<Contact>> fetchAll() {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<Contact> list = session.createQuery("FROM Contact", Contact.class).list();
            return Optional.of(list);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean update(Long id, Contact contact) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact existingContact = session.get(Contact.class, id);
            if (existingContact != null) {
                existingContact.setFirstName(contact.getFirstName());
                existingContact.setLastName(contact.getLastName());
                existingContact.setPhone(contact.getPhone());
                session.merge(existingContact);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = session.get(Contact.class, id);
            if (contact != null) {
                session.remove(contact);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Contact> fetchById(Long id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Contact contact = session.get(Contact.class, id);
            return Optional.ofNullable(contact);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
