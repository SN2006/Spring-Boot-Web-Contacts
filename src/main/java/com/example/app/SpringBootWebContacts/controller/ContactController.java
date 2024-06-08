package com.example.app.SpringBootWebContacts.controller;

import com.example.app.SpringBootWebContacts.entity.Contact;
import com.example.app.SpringBootWebContacts.exceptions.ContactDataException;
import com.example.app.SpringBootWebContacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public String findContacts(Model model) {
        List<Contact> contacts = contactService.findAll();
        model.addAttribute("contacts", contacts);
        model.addAttribute("title", "Contacts");
        model.addAttribute("fragmentName", "contact_list");
        return "layout";
    }

    @GetMapping("/create-contact")
    public String createContact(Model model) {
        model.addAttribute("title", "Create Contact");
        model.addAttribute("fragmentName", "create_contact");
        model.addAttribute("contact", new Contact());
        model.addAttribute("errors", new HashMap<String, String>());
        return "layout";
    }

    @GetMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        Contact contact = contactService.findById(id);
        model.addAttribute("title", "Update Contact");
        model.addAttribute("fragmentName", "update_contact");
        model.addAttribute("contact", contact);
        model.addAttribute("errors", new HashMap<String, String>());
        return "layout";
    }

    @PostMapping("/contacts")
    public String saveContact(@ModelAttribute Contact contact, Model model){
        try{
            contactService.save(contact);
            return "redirect:/contacts";
        }catch (ContactDataException e){
            model.addAttribute("title", "Create Contact");
            model.addAttribute("fragmentName", "create_contact");
            model.addAttribute("contact", contact);
            model.addAttribute("errors", e.getErrors());
            return "layout";
        }
    }

    @PutMapping("/contacts/{id}")
    public String updateContact(@PathVariable("id") Long id,
                                @ModelAttribute Contact contact,
                                Model model){
        try{
            contactService.update(contact, id);
            return "redirect:/contacts";
        }catch (ContactDataException e){
            model.addAttribute("title", "Update Contact");
            model.addAttribute("fragmentName", "update_contact");
            model.addAttribute("contact", contact);
            model.addAttribute("errors", e.getErrors());
            return "layout";
        }
    }

    @DeleteMapping("/contacts/{id}")
    public String deleteContact(@PathVariable("id") Long id) {
        contactService.delete(id);
        return "redirect:/contacts";
    }
}
