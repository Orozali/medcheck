package com.med.check.db.service.impl;

import com.med.check.db.dto.request.AnalysisRequest;
import com.med.check.db.dto.response.GetAnalysisResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.repository.AnalysisRepository;
import com.med.check.db.service.AnalysisService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisServiceImpl implements AnalysisService {

    private final AnalysisRepository analysisRepository;
    private final MessageSource messageSource;
    @Override
    public List<GetAnalysisResponse> getAllAnalysisByCategoryId(Long categoryId) {
        List<GetAnalysisResponse> list = new ArrayList<>();
        analysisRepository.findByAnalysisCategory_Id(categoryId)
                .forEach(item -> {
                    var response = GetAnalysisResponse.builder()
                            .id(item.getId())
                            .name(item.getName())
                            .price(item.getPrice())
                            .material(item.getMaterial())
                            .materialCollectionTime(item.getMaterialCollectionTime())
                            .conditionPreparingForAnalysis(item.getConditionPreparingForAnalysis())
                            .build();
                    list.add(response);
                });
        return list;
    }

    @Override
    public GetAnalysisResponse getAnalysisById(Long id) {
        var analysis = analysisRepository.findById(id).orElseThrow();
        return GetAnalysisResponse.builder()
                .id(analysis.getId())
                .name(analysis.getName())
                .price(analysis.getPrice())
                .conditionPreparingForAnalysis(analysis.getConditionPreparingForAnalysis())
                .timeIssuingResults(analysis.getTimeIssuingResults())
                .materialCollectionTime(analysis.getMaterialCollectionTime())
                .build();
    }

    @Override
    public SimpleResponse deleteById(Long analysisId) {
        if(analysisRepository.findById(analysisId).isPresent()) {
            analysisRepository.deleteById(analysisId);
        }else throw new NotFoundException(String.format("Analysis with ID %s not found!", analysisId));
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(messageSource.getMessage("admin.analysis.delete",
                        null, LocaleContextHolder.getLocale()))
                .build();
    }

    @Override
    public SimpleResponse editAnalysis(AnalysisRequest request) {
        var analysis = analysisRepository.findById(request.id()).orElseThrow();
        analysis.setName(request.name());
        analysis.setPrice(request.price());
        analysis.setMaterial(request.material());
        analysis.setMaterialCollectionTime(request.materialCollectionTime());
        analysis.setConditionPreparingForAnalysis(request.conditionPreparingForAnalysis());
        analysis.setTimeIssuingResults(request.timeIssuingResults());
        analysisRepository.save(analysis);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(messageSource.getMessage("admin.analysis.edit",
                        null, LocaleContextHolder.getLocale()))
                .build();
    }
}
