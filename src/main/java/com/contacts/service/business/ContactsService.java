package com.contacts.service.business;

import com.contacts.dto.request.ContactsRequest;
import com.contacts.dto.response.ContactsResponse;
import com.contacts.entity.Contacts;
import com.contacts.exception.NameNotFoundException;
import com.contacts.repository.ContactsRepository;
import com.contacts.service.IContactsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactsService implements IContactsService {

    private final ContactsRepository contactsRepository;
    private final ModelMapper modelMapper;

    public ContactsService(ContactsRepository contactsRepository, ModelMapper modelMapper) {
        this.contactsRepository = contactsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContactsResponse> addPersonToContacts(List<ContactsRequest> contactsRequest) {
        var response = contactsRequest.stream().map( contactRequest -> {
            var person = modelMapper.map(contactRequest, Contacts.class);
            var contacts = contactsRepository.findByNameAndAndLastName(person.getName(), person.getLastName());
            if(!contacts.isPresent()){
                var phoneFormatOrganized = phoneFormatOrganize(person.getPhones());

                person.setPhones(phoneFormatOrganized);
                contactsRepository.save(person);
                return modelMapper.map(person,ContactsResponse.class);
            }else{
                var phones = contacts.get().getPhones();
                var phoneFormatOrganized =  phoneFormatOrganize(person.getPhones());
                for (String phone: phoneFormatOrganized) {        //stream'e çevir
                    phones.add(phone);
                }
                var savedContacts = contactsRepository.save(contacts.get());
                return modelMapper.map(savedContacts,ContactsResponse.class);
            }
        }).toList();
        return response;
    }

    public Set<String> phoneFormatOrganize(Set phones) {
        var setPhones =(Set<String>) phones.stream()
                                        .map(   phone ->
                                              phone.toString().replace(" ","").replace("+90", ""))
                                        .collect(Collectors.toSet());
        return setPhones;
    }

    /*public Set<String> phoneAlreadyExistControl(Set<String> phones){
        var test = contactsRepository.findByPhones(phones);
        return test;
        var test = phones.stream().map( phone -> {
            var existControl = contactsRepository.findByPhonesExists(phone);
            if(existControl){
                phones.remove(phone);
            }
            return phone;
        }).collect(Collectors.toSet());
        return test;
    }*/

    @Override
    public List<ContactsResponse> getContactsByName(String name) throws NameNotFoundException {
        var contactsList = contactsRepository.findByName(name);
        if (!contactsList.isEmpty()){
            var contactResponseList = contactsList.stream().map(contacts -> modelMapper.map(contacts,ContactsResponse.class)).toList();
            return contactResponseList;
        }else
            throw new NameNotFoundException("the person with name: '" + name +"' not found!!");
    }
}
