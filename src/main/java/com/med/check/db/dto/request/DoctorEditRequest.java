package com.med.check.db.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record DoctorEditRequest (
        Long id,
        String service,
        String name,
        String surName,
        String position,
        String description,
        MultipartFile image
){
}
