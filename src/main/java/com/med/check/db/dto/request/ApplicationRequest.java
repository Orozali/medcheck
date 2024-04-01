package com.med.check.db.dto.request;

import com.med.check.db.validation.annotation.PhoneNumberValid;
import jakarta.validation.constraints.NotBlank;

public record ApplicationRequest (

        @NotBlank(message = "validation.name")
        String name,
        @PhoneNumberValid(message = "validation.phoneNumberValid")
        @NotBlank(message = "validation.telNumber")
        String tel_number
){
}
