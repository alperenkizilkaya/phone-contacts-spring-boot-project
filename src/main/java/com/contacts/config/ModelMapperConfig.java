package com.contacts.config;

import com.contacts.dto.request.ContactsRequest;
import com.contacts.dto.response.ContactsResponse;
import com.contacts.entity.Contacts;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class ModelMapperConfig {

    private static final Converter<ContactsRequest, Contacts> CONTACTS_REQUEST_TO_CONTACTS_CONVERTER =
            context -> {
                    var contacts = new Contacts();
                    contacts.setName(context.getSource().getName().toUpperCase(Locale.ROOT));
                    contacts.setLastName(context.getSource().getLastName().toUpperCase(Locale.ROOT));
                    contacts.getPhones().add(context.getSource().getPhone());
                    return contacts;
            };


    private static final Converter<Contacts, ContactsResponse> CONTACTS_TO_CONTACTS_RESPONSE_CONVERTER =
            context -> {
                var contactsResponse = new ContactsResponse();
                contactsResponse.setName(context.getSource().getName());
                contactsResponse.setLastName(context.getSource().getLastName());
                contactsResponse.setPhones(context.getSource().getPhones());
                return contactsResponse;
            };

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(CONTACTS_REQUEST_TO_CONTACTS_CONVERTER, ContactsRequest.class, Contacts.class);
        modelMapper.addConverter(CONTACTS_TO_CONTACTS_RESPONSE_CONVERTER, Contacts.class, ContactsResponse.class);

        return modelMapper;
    }
}
