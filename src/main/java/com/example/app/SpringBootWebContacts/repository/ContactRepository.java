package com.example.app.SpringBootWebContacts.repository;

import com.example.app.SpringBootWebContacts.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
}
