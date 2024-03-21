package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record GetAnalysisCategoryResponse(
        Long id,
        String name
) {
}
