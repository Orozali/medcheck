package com.med.check.db.service.impl;

import com.med.check.db.dto.request.ReviewRequest;
import com.med.check.db.dto.response.ReviewsResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.exception.exceptions.NotFoundException;
import com.med.check.db.model.Doctor;
import com.med.check.db.model.Patient;
import com.med.check.db.model.Reviews;
import com.med.check.db.model.User;
import com.med.check.db.repository.DoctorRepository;
import com.med.check.db.repository.PatientRepository;
import com.med.check.db.repository.ReviewsRepository;
import com.med.check.db.repository.UserInfoRepository;
import com.med.check.db.service.ReviewsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final DoctorRepository doctorRepository;
    private final MessageSource messageSource;
    private final UserInfoRepository userInfoRepository;
    private final PatientRepository patientRepository;


    @Override
    public List<ReviewsResponse> getAllReviews() {
        List<ReviewsResponse> reviewsResponses = new ArrayList<>();
        reviewsRepository.findAll().forEach( reviews -> {
            var response = ReviewsResponse.builder()
                    .patient_image(reviews.getPatient().getImage())
                    .name(reviews.getPatient().getFirstName())
                    .grade(reviews.getGrade())
                    .comment(reviews.getComment())
                    .build();
            reviewsResponses.add(response);
        });
        return reviewsResponses;
    }

    @Override
    public SimpleResponse addReview(ReviewRequest request) {
        Doctor doctor = doctorRepository.findById(request.doctor_id()).orElseThrow();
        Patient patient = patientRepository.findPatientByUserId(getAuthenticate().getId())
                .orElseThrow(()->new NotFoundException("User not found!"));
        var review = Reviews.builder()
                .grade(request.grade())
                .comment(request.comment())
                .doctor(doctor)
                .patient(patient)
                .build();
        reviewsRepository.save(review);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(messageSource.getMessage("user.review.add",null, LocaleContextHolder.getLocale()))
                .build();
    }

    private User getAuthenticate() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        log.info("Token has been taken!");
        return userInfoRepository.findByEmail(login).orElseThrow(() -> {
            log.error("User not found!");
            return new NotFoundException("User not found!");
        });
    }
}
