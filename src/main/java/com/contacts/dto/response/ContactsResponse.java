package com.contacts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsResponse {

    private String name;
    private String lastName;
    private Set<String> phones;

}
