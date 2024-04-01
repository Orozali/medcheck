package com.med.check.db.api.admin;

import com.med.check.db.dto.request.ScheduleRequest;
import com.med.check.db.dto.response.DoctorResponse;
import com.med.check.db.dto.response.ServiceResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.service.DoctorService;
import com.med.check.db.service.ScheduleService;
import com.med.check.db.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/appointment")
@RequiredArgsConstructor
@Tag(name = "Admin Appointment API")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminAppointmentApi {

    private final DoctorService doctorService;
    private final ScheduleService scheduleService;
    private final ServiceService service;
    @GetMapping("/services")
    @Operation(summary = "Get Service method", description = "This method is for getting Services!")
    public List<ServiceResponse> getService(){
        return service.getService();
    }

    @PostMapping("/doctor/{service_id}")
    @Operation(summary = "Get Doctor by service id", description = "This method gets doctors by service id!")
    public List<DoctorResponse> getDoctorsByServiceId(@PathVariable("service_id") Long service_id) {
        return doctorService.getDoctorsByServiceId(service_id);
    }
    @PostMapping("/add-appointment")
    @Operation(summary = "Add appointment method", description = "This method is for adding appointments to doctors!")
    public SimpleResponse addAppointment(@RequestBody @Valid ScheduleRequest request){
        return scheduleService.saveSchedule(request);
    }

}