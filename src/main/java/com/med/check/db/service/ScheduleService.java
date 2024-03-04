package com.med.check.db.service;

import com.med.check.db.dto.request.ScheduleRequest;
import com.med.check.db.dto.response.SimpleResponse;

public interface ScheduleService {
    SimpleResponse saveSchedule(ScheduleRequest request);
}
