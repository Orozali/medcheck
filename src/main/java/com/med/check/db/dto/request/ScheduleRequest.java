package com.med.check.db.dto.request;

import com.med.check.db.model.enums.Days;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public record ScheduleRequest(
        Long service_id,
        Long doctor_id,
        LocalDate date_of_start,
        LocalDate date_of_finish,
        LocalTime time_from,
        LocalTime time_to,
        int interval,
        Map<Days, Boolean> repeat_days
) {
}
