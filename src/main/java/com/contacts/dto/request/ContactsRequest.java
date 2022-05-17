package com.contacts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsRequest {

    @NotNull
    @NotBlank
    @Size(min = 3,max = 25)
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 3,max = 25)
    private String lastName;
    // "+ulkeKodu" ile başlamak zorunda...
    @Size(max = 15) @Pattern(message = "phone format example: +90 555 444 33 22",regexp="(^[+][0-9]{1,4}[0-9]*$)")
    private String phone;

}
