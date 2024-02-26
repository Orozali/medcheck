package com.med.check.db.service;

import com.med.check.db.dto.request.AddDoctorRequest;
import com.med.check.db.dto.response.SimpleResponse;

public interface DoctorService {

    SimpleResponse addDoctor(AddDoctorRequest request);
}
