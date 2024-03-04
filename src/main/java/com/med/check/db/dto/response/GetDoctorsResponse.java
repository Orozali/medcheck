package com.med.check.db.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record GetDoctorsResponse(
        Long doctor_id,
        boolean isActive,
        String image,
        String name,
        String surName,
        String service,
        String position,
        LocalDate schedule_to
) {
}
