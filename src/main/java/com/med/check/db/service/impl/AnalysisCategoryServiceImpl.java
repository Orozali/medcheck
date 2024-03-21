package com.med.check.db.service.impl;

import com.med.check.db.dto.request.AnalysisCategoryRequest;
import com.med.check.db.dto.response.GetAnalysisCategoryResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.exception.exceptions.AlreadyExistException;
import com.med.check.db.model.AnalysisCategory;
import com.med.check.db.repository.AnalysisCategoryRepository;
import com.med.check.db.service.AnalysisCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisCategoryServiceImpl implements AnalysisCategoryService{

    private final AnalysisCategoryRepository analysisCategoryRepository;
    private final MessageSource messageSource;
    @Override
    public List<GetAnalysisCategoryResponse> getAll() {
        List<GetAnalysisCategoryResponse> analysisCategories = new ArrayList<>();
        analysisCategoryRepository.findAll().forEach(item -> {
            var analysisCategory = GetAnalysisCategoryResponse.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .build();
            analysisCategories.add(analysisCategory);
        });
        return analysisCategories;
    }

    @Override
    public SimpleResponse add(AnalysisCategoryRequest request) {
        if(analysisCategoryRepository.findByName(request.name()).isPresent()){
            throw new AlreadyExistException(messageSource.getMessage("admin.analysis.category.alreadyExist",
                    new Object[]{request.name()}, LocaleContextHolder.getLocale()));
        }
        analysisCategoryRepository.save(AnalysisCategory.builder()
                .name(request.name())
                .build()
        );
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(messageSource.getMessage("admin.analysis.category.create",
                        null, LocaleContextHolder.getLocale()))
                .build();
    }

    @Override
    public SimpleResponse delete(Long analysisCategoryId) {
        analysisCategoryRepository.deleteById(analysisCategoryId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(messageSource.getMessage("admin.analysis.category.delete",
                        null, LocaleContextHolder.getLocale()))
                .build();
    }
}
