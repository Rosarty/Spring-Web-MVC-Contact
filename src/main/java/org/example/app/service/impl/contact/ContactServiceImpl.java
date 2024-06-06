package org.example.app.service.impl.contact;

import jakarta.transaction.Transactional;
import org.example.app.entity.Contact;
import org.example.app.repository.impl.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository repository;

    @Override
    @Transactional
    public boolean create(Contact member) {
        return repository.create(member);
    }

    @Override
    @Transactional
    public List<Contact> fetchAll() {
        return repository.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Contact fetchById(Long id) {
        return repository.fetchById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean update(Long id, Contact member) {
        return repository.update(id, member);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<Contact> optional = repository.fetchById(id);
        if (optional.isPresent())
            return repository.delete(id);
        else return false;
    }
}
