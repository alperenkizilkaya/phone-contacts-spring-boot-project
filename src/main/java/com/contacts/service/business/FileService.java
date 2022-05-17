package com.contacts.service.business;

import com.contacts.dto.request.ContactsRequest;
import com.contacts.dto.response.ContactsResponse;
import com.contacts.exception.WrongPhoneFormatException;
import com.contacts.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class FileService implements IFileService {

    private final ContactsService contactsService;


    public FileService(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @Override
    public List<ContactsResponse> read(MultipartFile file) throws IOException, WrongPhoneFormatException {
        var fileToByte = file.getResource().getInputStream().readAllBytes();
        String byteToString = new String(fileToByte);

        Scanner scannerTxt = new Scanner(byteToString);
        String txtLine;
        List<ContactsRequest> contactsRequest = new ArrayList<>();
        while(scannerTxt.hasNextLine()){
            txtLine = scannerTxt.nextLine();
            contactsRequest.add(fileToContactsRequest(txtLine));
        }

        return contactsService.addPersonToContacts(contactsRequest);
    }

    ContactsRequest fileToContactsRequest(String txtLine) throws WrongPhoneFormatException {
        //alperen,kizilkaya,+905532644256  -> txt format to read
        String[] seperateForContacts = txtLine.split(",");
        var txtName = seperateForContacts[0].toUpperCase(Locale.ROOT);
        var txtLastName= seperateForContacts[1].toUpperCase(Locale.ROOT);
        var txtPhone = seperateForContacts[2];
        if(!txtPhone.startsWith("+")){
            throw new WrongPhoneFormatException("phone format is wrong : " + txtPhone );
        }
        /*
                        for multiple phoneNumbers in one line ->
                            example: //alperen,kizilkaya,+905532644256,+905554443322
        Set<String> txtPhones = new HashSet<>();
        for (int i=2; i< seperateForContacts.length; i++){
            txtPhones.add(seperateForContacts[i]);
        }
        */
        var contactsRequest = new ContactsRequest();
        contactsRequest.setName(txtName);
        contactsRequest.setLastName(txtLastName);
        contactsRequest.setPhone(txtPhone);
        return contactsRequest;
    }
}
