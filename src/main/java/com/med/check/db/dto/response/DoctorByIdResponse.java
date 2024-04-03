package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record DoctorByIdResponse(
        Long id,
        String image,
        String name,
        String surName,
        String service,
        String position,
        String description,
        double grade
) {
}
