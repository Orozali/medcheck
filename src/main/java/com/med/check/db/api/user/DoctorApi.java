package com.med.check.db.api.user;

import com.med.check.db.dto.response.DoctorReviewResponse;
import com.med.check.db.service.DoctorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
