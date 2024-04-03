package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record DoctorAllResponse(
        Long doctor_id,
        String image,
        String name,
        String surName,
        String position
){
}
