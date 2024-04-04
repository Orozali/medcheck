package com.med.check.db.service;

import com.med.check.db.dto.request.AddDoctorRequest;
import com.med.check.db.dto.request.DoctorEditRequest;
import com.med.check.db.dto.request.DoctorImageRequest;
import com.med.check.db.dto.response.*;

import java.io.IOException;
import java.util.List;

public interface DoctorService {

    SimpleResponse addDoctor(DoctorImageRequest request) throws IOException;

    List<GetDoctorsResponse> getDoctors();

    DoctorByIdResponse getDoctorById(Long doctorId) throws IOException;

    SimpleResponse editDoctorById(DoctorEditRequest request) throws IOException;

    List<DoctorResponse> getDoctorsByServiceId(Long serviceId);

    List<DoctorReviewResponse> getDoctorsByReview();

    List<DoctorAllResponse> getAllDoctors();
}
