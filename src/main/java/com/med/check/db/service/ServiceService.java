package com.med.check.db.service;

import com.med.check.db.dto.response.ServiceResponse;
import com.med.check.db.dto.response.SimpleResponse;

import java.util.List;

public interface ServiceService {
    List<ServiceResponse> getService();

    SimpleResponse createService(String serviceName);

    SimpleResponse deleteServiceById(Long id);
}
