package com.med.check.db.service;

import com.med.check.db.dto.request.AddDoctorRequest;
import com.med.check.db.dto.response.*;

import java.util.List;

public interface DoctorService {

    SimpleResponse addDoctor(AddDoctorRequest request);

    List<GetDoctorsResponse> getDoctors();

    DoctorByIdResponse getDoctorById(Long doctorId);

    SimpleResponse editDoctorById(DoctorByIdResponse request);

    List<DoctorResponse> getDoctorsByServiceId(Long serviceId);

    List<DoctorReviewResponse> getDoctorsByReview();

    List<DoctorAllResponse> getAllDoctors();
}
