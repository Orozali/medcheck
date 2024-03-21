package com.med.check.db.dto.response;

import lombok.Builder;

import java.util.Locale;

@Builder
public record GetAnalysisCategoryResponse(
        Long id,
        String name
) {
}
