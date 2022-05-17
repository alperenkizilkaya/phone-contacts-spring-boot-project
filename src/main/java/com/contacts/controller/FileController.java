package com.contacts.controller;

import com.contacts.dto.response.ContactsResponse;
import com.contacts.exception.WrongPhoneFormatException;
import com.contacts.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/importTxtFile")
@CrossOrigin
@RequestScope
@RequiredArgsConstructor
@Validated
public class FileController {

    private final IFileService fileService;

    @PostMapping
    public List<ContactsResponse> uploadFile(@RequestParam("file") MultipartFile file) throws Exception, WrongPhoneFormatException {
        String message = "";
        try {
            message = "reading proceses is starting for: " + file.getName();
            System.err.println(message);
            return fileService.read(file);
        } catch (Exception e) {
            throw new Exception("file error");
        }
    }
}
