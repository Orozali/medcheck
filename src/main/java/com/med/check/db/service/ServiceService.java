package com.med.check.db.service;

import com.med.check.db.dto.response.ServiceResponse;

import java.util.List;

public interface ServiceService {
    List<ServiceResponse> getService();
}
