package com.med.check.db.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddDoctorRequest(
        String image,
        @NotBlank(message = "Имя не должна быть пустой!")
        String name,
        @NotBlank(message = "Фамилия не должна быть пустой!")
        String surname,
        @NotBlank(message = "Отделение не должна быть пустой!")
        String service,
        @NotBlank(message = "Должность не должна быть пустой!")
        String position,
        @NotBlank(message = "Описание не должна быть пустой!")
        String description
) {
}
