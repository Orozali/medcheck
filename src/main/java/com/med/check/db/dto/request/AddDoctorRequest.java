package com.med.check.db.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddDoctorRequest(
        String image,
        @NotBlank(message = "validation.name")
        String name,
        @NotBlank(message = "validation.surName")
        String surname,
        @NotBlank(message = "validation.service")
        String service,
        @NotBlank(message = "validation.position")
        String position,
        @NotBlank(message = "validation.description")
        String description
) {
}
