package org.example.app.service.impl.contact;

import org.example.app.entity.Contact;
import org.example.app.service.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contact> {
    boolean create(Contact member);
    List<Contact> fetchAll();
    Contact fetchById(Long id);
    boolean update(Long id, Contact member);
    boolean delete(Long id);
}
