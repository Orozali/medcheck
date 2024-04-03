package com.med.check.db.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorReviewResponse {
    private Long doctor_id;
    private String image;
    private String name;
    private String surName;
    private String position;
    private double grade;
}
