package com.contacts.controller;

import com.contacts.dto.request.ContactsRequest;
import com.contacts.dto.response.ContactsResponse;
import com.contacts.exception.NameNotFoundException;
import com.contacts.service.IContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@CrossOrigin
@RequestScope
@RequiredArgsConstructor
@Validated
public class ContactsController {

    private final IContactsService contactsService;

    @PostMapping
    public List<ContactsResponse> addPersonToContacts(@RequestBody @Validated List<ContactsRequest> contactsRequest){
        return contactsService.addPersonToContacts(contactsRequest);
    }

    @GetMapping
    public List<ContactsResponse> getContactsByName(@RequestParam String name) throws NameNotFoundException {
        return contactsService.getContactsByName(name);
    }

}
