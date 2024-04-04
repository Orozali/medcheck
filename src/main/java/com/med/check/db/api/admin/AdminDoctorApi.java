package com.med.check.db.api.admin;

import com.med.check.db.dto.request.DoctorEditRequest;
import com.med.check.db.dto.request.DoctorImageRequest;
import com.med.check.db.dto.response.DoctorByIdResponse;
import com.med.check.db.dto.response.GetDoctorsResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/doctor")
@RequiredArgsConstructor
@Tag(name = "Admin Doctor API")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminDoctorApi {

    private final DoctorService doctorService;

    @PostMapping("/add-doctor")
    @Operation(summary = "Add doctor method", description = "This method adds doctor to database!")
    public SimpleResponse addDoctor(@ModelAttribute @Valid DoctorImageRequest request) throws IOException {
        return doctorService.addDoctor(request);
    }

    @GetMapping("/get-doctors")
    @Operation(summary = "Get doctors method", description = "This method gets doctors from database!")
    public List<GetDoctorsResponse> getDoctors(){
        return doctorService.getDoctors();
    }

    @GetMapping("/get-doctor-by-id/{doctor_id}")
    @Operation(summary = "Get doctor method", description = "This method gets one doctor by ID!")
    public DoctorByIdResponse getDoctor(@PathVariable("doctor_id") Long doctor_id) throws IOException {
        return doctorService.getDoctorById(doctor_id);
    }

    @PostMapping("/edit")
    @Operation(summary = "Edit doctor method", description = "This method edits one doctor!")
    public SimpleResponse editDoctor(@ModelAttribute @Valid DoctorEditRequest request) throws IOException {
        return doctorService.editDoctorById(request);
    }

}
