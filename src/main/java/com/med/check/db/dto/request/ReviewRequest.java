package com.med.check.db.dto.request;

public record ReviewRequest (
        Long doctor_id,
        int grade,
        String comment
){
}
