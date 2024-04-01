package com.med.check.db.service.impl;

import com.med.check.db.dto.request.ApplicationRequest;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.model.Application;
import com.med.check.db.repository.ApplicationRepository;
import com.med.check.db.service.ApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final MessageSource messageSource;

    @Override
    public SimpleResponse addApplication(ApplicationRequest request) {
        var application = Application.builder()
                .name(request.name())
                .phoneNumber(request.tel_number())
                .date(LocalDate.now())
                .build();
        applicationRepository.save(application);
        log.info("Application successfully saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(messageSource.getMessage("user.application.add", null, LocaleContextHolder.getLocale()))
                .build();
    }
}
