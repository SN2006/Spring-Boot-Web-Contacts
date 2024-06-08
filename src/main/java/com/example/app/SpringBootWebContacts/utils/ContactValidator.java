package com.example.app.SpringBootWebContacts.utils;

import com.example.app.SpringBootWebContacts.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ContactValidator {

    private static final String PHONE_REGEX = "[0-9]{3} [0-9]{3}-[0-9]{4}";

    public Map<String, String> validate(Contact contact){
        Map<String, String> errors = new HashMap<>();
        if (contact.getName() == null || contact.getName().trim().isEmpty()) {
            errors.put("name", Constants.EMPTY_NAME_MSG);
        }
        if (contact.getSurname() == null || contact.getSurname().trim().isEmpty()) {
            errors.put("surname", Constants.EMPTY_SURNAME_MSG);
        }
        if (contact.getPhone() == null || contact.getPhone().trim().isEmpty()) {
            errors.put("phone", Constants.EMPTY_PHONE_MSG);
        }else if (!contact.getPhone().matches(PHONE_REGEX)){
            errors.put("phone", Constants.INVALID_PHONE_MSG);
        }
        return errors;
    }

}
