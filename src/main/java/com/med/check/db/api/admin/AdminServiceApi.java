package com.med.check.db.api.admin;

import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.service.ServiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/service")
@CrossOrigin(origins = "*", maxAge = 3000)
@Tag(name = "Admin Service API")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminServiceApi {
    private final ServiceService service;

    @PostMapping("/create")
    public SimpleResponse createService(@RequestBody String serviceName){
        return service.createService(serviceName);
    }

    @DeleteMapping("/delete/{id}")
    public SimpleResponse deleteService(@PathVariable("id") Long id){
        return service.deleteServiceById(id);
    }
}
