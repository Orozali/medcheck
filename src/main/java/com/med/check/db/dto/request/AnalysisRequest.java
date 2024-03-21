package com.med.check.db.dto.request;

public record AnalysisRequest(
        Long id,
        String name,
        int price,
        String material,
        String conditionPreparingForAnalysis,
        String materialCollectionTime,
        String timeIssuingResults
) {
}
