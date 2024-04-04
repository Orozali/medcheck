package com.med.check.db.api.user;

import com.med.check.db.dto.response.DoctorAllResponse;
import com.med.check.db.dto.response.DoctorByIdResponse;
import com.med.check.db.dto.response.DoctorResponse;
import com.med.check.db.dto.response.DoctorReviewResponse;
import com.med.check.db.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
@Tag(name = "Doctor API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DoctorApi {

    private final DoctorService doctorService;

    @GetMapping("/get-doctors-by-review")
    @PreAuthorize("hasAuthority('PATIENT')")
    public List<DoctorReviewResponse> getDoctorsByReview(){
        return doctorService.getDoctorsByReview();
    }

    @GetMapping("/get-all-doctors")
    @Operation(summary = "Get doctors method",
            description = "This method gets all doctors from database!")
    public List<DoctorAllResponse> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/get-doctors-by-service/{id}")
    @Operation(summary = "Get doctors by service id method",
            description = "This method gets all doctors from database by service id!")
    public List<DoctorResponse> getDoctorsByService(@PathVariable("id") Long service_id){
        return doctorService.getDoctorsByServiceId(service_id);
    }

    @GetMapping("/get-doctor-by-id/{id}")
    @Operation(summary = "Get doctor by id method",
            description = "This method gets doctor from database by id!")
    public DoctorByIdResponse getDoctorById(@PathVariable("id") Long doctor_id) throws IOException {
        return doctorService.getDoctorById(doctor_id);
    }

}
