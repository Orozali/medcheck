package com.med.check.db.service.impl;

import com.med.check.db.dto.request.AddDoctorRequest;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.model.Doctor;
import com.med.check.db.repository.DoctorRepository;
import com.med.check.db.repository.ServiceRepository;
import com.med.check.db.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public SimpleResponse addDoctor(AddDoctorRequest request) {
        com.med.check.db.model.Service service = serviceRepository.findByName(request.service())
                .orElseThrow(()->{
                    log.error("Service not found!");
                    return new NotFoundException("Service not found!");
                } );
        Doctor doctor = Doctor.builder()
                .service(service)
                .description(request.description())
                .firstName(request.name())
                .lastName(request.surname())
                .position((request.position()))
                .isActive(true)
                .image(request.image())
                .build();
        service.addDoctor(doctor);
        doctorRepository.save(doctor);
        log.info("Новый специялист успешно добавлен!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Новый специялист успешно добавлен!")
                .build();
    }
}


