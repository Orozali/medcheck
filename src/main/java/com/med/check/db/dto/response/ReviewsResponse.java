package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record ReviewsResponse (
        String patient_image,
        String name,
        int grade,
        String comment
){
}
