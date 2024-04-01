package com.med.check.db.service;

import com.med.check.db.dto.request.ApplicationRequest;
import com.med.check.db.dto.response.SimpleResponse;

public interface ApplicationService {

    SimpleResponse addApplication(ApplicationRequest request);
}
