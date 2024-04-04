package com.med.check.db.service.impl;

import com.med.check.db.dto.request.AddDoctorRequest;
import com.med.check.db.dto.request.DoctorEditRequest;
import com.med.check.db.dto.request.DoctorImageRequest;
import com.med.check.db.dto.response.*;
import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.model.Doctor;
import com.med.check.db.model.ImageData;
import com.med.check.db.model.Reviews;
import com.med.check.db.model.Schedule;
import com.med.check.db.repository.DoctorRepository;
import com.med.check.db.repository.ServiceRepository;
import com.med.check.db.repository.StorageRepository;
import com.med.check.db.service.DoctorService;
import com.med.check.db.utils.ImageUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ServiceRepository serviceRepository;
    private final StorageRepository storageRepository;

    @Override
    public SimpleResponse addDoctor(DoctorImageRequest request) throws IOException {
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
                .build();
        if(request.image() != null){
            ImageData image = storageRepository.save(ImageData.builder()
                    .type(request.image().getContentType())
                    .name(request.image().getOriginalFilename())
                    .imageData(request.image().getBytes())
                    .build());
            doctor.setImage(image);
        }

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
                    .image(ImageUtils.getBase64Image(doctor.getImage()))
                    .position(doctor.getPosition())
                    .service(doctor.getService().getName())
                    .schedule_to(time)
                    .build();
            list.add(response);
        });
        return list;
    }

    @Override
    public DoctorByIdResponse getDoctorById(Long doctorId){
        DecimalFormat df = new DecimalFormat("#.##");
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()->{
                    log.error("Doctor not found!");
                    return new NotFoundException("Doctor not found!");
                });
        List<Reviews> reviews = doctor.getReviews();
        double count = 0;
        for(Reviews review: reviews){
            count+=review.getGrade();
        }
        if(!reviews.isEmpty()){
            count = count/reviews.size();
        }
        return DoctorByIdResponse.builder()
                .id(doctor.getId())
                .name(doctor.getFirstName())
                .surName(doctor.getLastName())
                .image(ImageUtils.getBase64Image(doctor.getImage()))
                .description(doctor.getDescription())
                .position(doctor.getPosition())
                .service(doctor.getService().getName())
                .grade(Double.parseDouble(df.format(count)))
                .build();
    }

    @Override
    public SimpleResponse editDoctorById(DoctorEditRequest request) throws IOException {
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
        if(request.image() != null){
            ImageData image = storageRepository.save(ImageData.builder()
                    .name(request.image().getOriginalFilename())
                    .type(request.image().getContentType())
                    .imageData(request.image().getBytes())
                    .build());

            doctor.setFirstName(request.name());
            doctor.setLastName(request.surName());
            doctor.setService(service);
            doctor.setDescription(request.description());
            doctor.setPosition(request.position());
            doctor.setImage(image);
        }
        doctor.setFirstName(request.name());
        doctor.setLastName(request.surName());
        doctor.setService(service);
        doctor.setDescription(request.description());
        doctor.setPosition(request.position());

        log.info("Cпециялист успешно обновлен!");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Cпециялист успешно обновлен!")
                .build();
    }

    @Override
    public List<DoctorResponse> getDoctorsByServiceId(Long serviceId) {

        com.med.check.db.model.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() ->new NotFoundException("Service Not Found"));

            List<DoctorResponse> result = new ArrayList<>();
            service.getDoctors().forEach(doctor -> {
                var response = DoctorResponse.builder()
                        .doctor_id(doctor.getId())
                        .positions(doctor.getPosition())
                        .name(doctor.getFirstName())
                        .surname(doctor.getLastName())
                        .image(ImageUtils.getBase64Image(doctor.getImage()))
                        .build();
                result.add(response);
            });
            return result;
    }

    @Override
    public List<DoctorReviewResponse> getDoctorsByReview() {
        DecimalFormat df = new DecimalFormat("#.##");
        List<DoctorReviewResponse> response = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.findAll();
        for(Doctor doctor: doctors){
            double count = 0;
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
            doctorReviewResponse.setImage(ImageUtils.getBase64Image(doctor.getImage()));
            doctorReviewResponse.setGrade(Double.parseDouble(df.format(count)));
            response.add(doctorReviewResponse);
        }
        response.sort(Comparator.comparingDouble(DoctorReviewResponse::getGrade).reversed());
        return response;
    }

    @Override
    public List<DoctorAllResponse> getAllDoctors() {
        List<DoctorAllResponse> responses = new ArrayList<>();
        doctorRepository.findAll().forEach( doctor -> {
            var response = DoctorAllResponse.builder()
                    .doctor_id(doctor.getId())
                    .image(ImageUtils.getBase64Image(doctor.getImage()))
                    .name(doctor.getFirstName())
                    .surName(doctor.getLastName())
                    .position(doctor.getPosition())
                    .build();
            responses.add(response);
        });
        return responses;
    }
}