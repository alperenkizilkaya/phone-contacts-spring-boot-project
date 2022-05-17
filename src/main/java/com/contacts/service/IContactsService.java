package com.contacts.service;

import com.contacts.dto.request.ContactsRequest;
import com.contacts.dto.response.ContactsResponse;
import com.contacts.exception.NameNotFoundException;

import java.util.List;

public interface IContactsService {
    List<ContactsResponse> addPersonToContacts(List<ContactsRequest> contactsRequest);

    List<ContactsResponse> getContactsByName(String name) throws NameNotFoundException;
}
