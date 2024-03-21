package com.med.check.db.service;

import com.med.check.db.dto.request.AnalysisCategoryRequest;
import com.med.check.db.dto.response.GetAnalysisCategoryResponse;
import com.med.check.db.dto.response.SimpleResponse;

import java.util.List;

public interface AnalysisCategoryService {
    List<GetAnalysisCategoryResponse> getAll();

    SimpleResponse add(AnalysisCategoryRequest request);

    SimpleResponse delete(Long analysisCategoryId);
}
