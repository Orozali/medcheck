package com.med.check.db.service.impl;

import com.med.check.db.dto.response.ServiceDoctorResponse;
import com.med.check.db.dto.response.ServiceResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.repository.ServiceRepository;
import com.med.check.db.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final MessageSource messageSource;

    @Override
    public List<ServiceResponse> getService() {
        List<ServiceResponse> services = new ArrayList<>();
        serviceRepository.findAll().forEach(service -> {
            var serviceResponse = ServiceResponse.builder()
                    .service_id(service.getId())
                    .name(service.getName())
                    .build();
            services.add(serviceResponse);
        });
        return services;
    }

    @Override
    public SimpleResponse createService(String serviceName) {
        com.med.check.db.model.Service service = com.med.check.db.model.Service
                .builder()
                .name(serviceName)
                .build();
        serviceRepository.save(service);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(messageSource.getMessage("admin.service.create",
                        null, LocaleContextHolder.getLocale()))
                .build();
    }

    @Override
    public SimpleResponse deleteServiceById(Long id) {
        serviceRepository.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(messageSource.getMessage("admin.service.delete",
                        null, LocaleContextHolder.getLocale()))
                .build();
    }
}
