package com.med.check.db.dto.response;

import lombok.Builder;

@Builder
public record GetAnalysisResponse(
        Long id,
        String name,
        int price,
        String material,
        String conditionPreparingForAnalysis,
        String materialCollectionTime,
        String timeIssuingResults

) {
}
