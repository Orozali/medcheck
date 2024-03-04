package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record DoctorResponse(
        Long doctor_id,
        String name,
        String surname,
        String image
) {
}
