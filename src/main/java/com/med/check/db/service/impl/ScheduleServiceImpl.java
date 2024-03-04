package com.med.check.db.service.impl;

import com.med.check.db.dto.request.ScheduleRequest;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.model.Doctor;
import com.med.check.db.model.Schedule;
import com.med.check.db.repository.DoctorRepository;
import com.med.check.db.repository.ScheduleRepository;
import com.med.check.db.repository.ServiceRepository;
import com.med.check.db.service.ScheduleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ServiceRepository serviceRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public SimpleResponse saveSchedule(ScheduleRequest request) {
        com.med.check.db.model.Service service = serviceRepository.findById(request.service_id())
                .orElseThrow(() -> {
                    log.info("Service not found");
                    return new NotFoundException("Service not found!");
                });
        Doctor doctor = doctorRepository.findById(request.doctor_id())
                .orElseThrow(() -> {
                    log.info("Doctor not found");
                    return new NotFoundException("Doctor not found!");
                });

        Schedule schedule = Schedule.builder()
                .service(service)
                .doctor(doctor)
                .dateOfStart(request.date_of_start())
                .dateOfFinish(request.date_of_finish())
                .interval(request.interval())
                .repeatDay(request.repeat_days())
                .build();
        scheduleRepository.save(schedule);
        log.info("Schedule successfully saved!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message("Schedule successfully saved!")
                .build();
    }
}
