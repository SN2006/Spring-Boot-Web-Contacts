package com.example.app.SpringBootWebContacts.service;

import com.example.app.SpringBootWebContacts.entity.Contact;
import com.example.app.SpringBootWebContacts.exceptions.ContactDataException;
import com.example.app.SpringBootWebContacts.repository.ContactRepository;
import com.example.app.SpringBootWebContacts.utils.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactValidator contactValidator;

    @Autowired
    public ContactService(ContactRepository contactRepository, ContactValidator contactValidator) {
        this.contactRepository = contactRepository;
        this.contactValidator = contactValidator;
    }

    public Contact save(Contact contact) {
        Map<String, String> errors = contactValidator.validate(contact);
        if (!errors.isEmpty()) {
            throw new ContactDataException("Check inputs", errors);
        }
        return contactRepository.save(contact);
    }

    public List<Contact> findAll() {
        Iterable<Contact> iterable = contactRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .toList();
    }

    public Contact findById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact update(Contact contact, Long id) {
        Map<String, String> errors = contactValidator.validate(contact);
        if (!errors.isEmpty()) {
            throw new ContactDataException("Check inputs", errors);
        }
        contact.setId(id);
        return contactRepository.save(contact);
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
