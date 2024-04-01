package com.med.check.db.service.impl;

import com.med.check.db.dto.request.AddDoctorRequest;
import com.med.check.db.dto.response.*;
import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.model.Doctor;
import com.med.check.db.model.Reviews;
import com.med.check.db.model.Schedule;
import com.med.check.db.repository.DoctorRepository;
import com.med.check.db.repository.ServiceRepository;
import com.med.check.db.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ServiceRepository serviceRepository;
    private final JdbcTemplate jdbcTemplate;

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

    @Override
    public List<GetDoctorsResponse> getDoctors() {
        List<GetDoctorsResponse> list = new ArrayList<>();
        doctorRepository.findAll().forEach(doctor -> {
            Schedule schedule =  doctor.getSchedule();
            LocalDate time = null;
            if(schedule != null) {
                time = schedule.getDateOfFinish();
            }
            var response = GetDoctorsResponse.builder()
                    .doctor_id(doctor.getId())
                    .isActive(doctor.getIsActive())
                    .name(doctor.getFirstName())
                    .surName(doctor.getLastName())
                    .image(doctor.getImage())
                    .position(doctor.getPosition())
                    .service(doctor.getService().getName())
                    .schedule_to(time)
                    .build();
            list.add(response);
        });
        return list;
    }

    @Override
    public DoctorByIdResponse getDoctorById(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()->{
                    log.error("Doctor not found!");
                    return new NotFoundException("Doctor not found!");
                });
        return DoctorByIdResponse.builder()
                .id(doctor.getId())
                .name(doctor.getFirstName())
                .surName(doctor.getLastName())
                .image(doctor.getImage())
                .description(doctor.getDescription())
                .position(doctor.getPosition())
                .service(doctor.getService().getName())
                .build();
    }

    @Override
    public SimpleResponse editDoctorById(DoctorByIdResponse request) {
        Doctor doctor = doctorRepository.findById(request.id())
                .orElseThrow(()->{
                    log.error("Doctor not found!");
                    return new NotFoundException("Doctor not found!");
                });
        com.med.check.db.model.Service service = serviceRepository.findByName(request.service())
                .orElseThrow(()->{
                    log.error("Service not found!");
                    return new NotFoundException("Service not found!");
                } );
        doctor.setFirstName(request.name());
        doctor.setLastName(request.surName());
        doctor.setService(service);
        doctor.setDescription(request.description());
        doctor.setPosition(request.position());
        doctor.setImage(request.image());
        log.info("Cпециялист успешно обновлен!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cпециялист успешно обновлен!")
                .build();
    }

    @Override
    public List<DoctorResponse> getDoctorsByServiceId(Long serviceId) {
        String sql = "select d.id as doctor_id, d.first_name as name, " +
                "d.last_name as surName, d.image as image from doctors d where service_id = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> new DoctorResponse(
                resultSet.getLong("doctor_id"),
                resultSet.getString("name"),
                resultSet.getString("surName"),
                resultSet.getString("image")
        ), serviceId);
    }

    @Override
    public List<DoctorReviewResponse> getDoctorsByReview() {
        List<DoctorReviewResponse> response = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.findAll();
        for(Doctor doctor: doctors){
            int count = 0;
            List<Reviews> reviews = doctor.getReviews();
            DoctorReviewResponse doctorReviewResponse = new DoctorReviewResponse();
            for(Reviews review: reviews){
                count+=review.getGrade();
            }
            if(!reviews.isEmpty()){
                count = count/reviews.size();
            }
            doctorReviewResponse.setDoctor_id(doctor.getId());
            doctorReviewResponse.setName(doctor.getFirstName());
            doctorReviewResponse.setSurName(doctor.getLastName());
            doctorReviewResponse.setPosition(doctor.getPosition());
            doctorReviewResponse.setImage(doctor.getImage());
            doctorReviewResponse.setGrade(count);
            response.add(doctorReviewResponse);
        }
        response.sort(Comparator.comparingInt(DoctorReviewResponse::getGrade).reversed());
        return response;
    }
}


