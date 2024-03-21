package com.med.check.db.service;

import com.med.check.db.dto.request.AnalysisRequest;
import com.med.check.db.dto.response.GetAnalysisResponse;
import com.med.check.db.dto.response.SimpleResponse;

import java.util.List;

public interface AnalysisService {
    List<GetAnalysisResponse> getAllAnalysisByCategoryId(Long categoryId);

    GetAnalysisResponse getAnalysisById(Long id);

    SimpleResponse deleteById(Long analysisId);

    SimpleResponse editAnalysis(AnalysisRequest request);
}
