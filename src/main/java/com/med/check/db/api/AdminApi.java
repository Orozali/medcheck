package com.med.check.db.api;

import com.med.check.db.dto.request.AddDoctorRequest;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminApi {

    private final DoctorService doctorService;

    @PostMapping("/add-doctor")
    @Operation(summary = "Add doctor method", description = "This method adds doctor to database!")
    public SimpleResponse addDoctor(@RequestBody AddDoctorRequest request){
        return doctorService.addDoctor(request);
    }


}
