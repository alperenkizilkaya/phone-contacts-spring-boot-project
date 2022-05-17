package com.contacts.service;

import com.contacts.dto.response.ContactsResponse;
import com.contacts.exception.WrongPhoneFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IFileService {

    List<ContactsResponse> read(MultipartFile file) throws IOException, WrongPhoneFormatException;
}
