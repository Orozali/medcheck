package com.med.check.db.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record DoctorImageRequest (
        @NotBlank(message = "validation.name")
        String name,
        @NotBlank(message = "validation.surName")
        String surname,
        @NotBlank(message = "validation.service")
        String service,
        @NotBlank(message = "validation.position")
        String position,
        @NotBlank(message = "validation.description")
        String description,
        MultipartFile image

){
}
