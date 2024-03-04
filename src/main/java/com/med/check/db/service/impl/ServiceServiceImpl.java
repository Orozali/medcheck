package com.med.check.db.service.impl;

import com.med.check.db.dto.response.ServiceDoctorResponse;
import com.med.check.db.dto.response.ServiceResponse;
import com.med.check.db.repository.ServiceRepository;
import com.med.check.db.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    @Override
    public List<ServiceResponse> getService() {
        List<ServiceResponse> services = new ArrayList<>();
        serviceRepository.findAll().forEach( service -> {
            var serviceResponse = ServiceResponse.builder()
                    .service_id(service.getId())
                    .name(service.getName())
                    .build();
            services.add(serviceResponse);
        });
        return services;
    }
}
