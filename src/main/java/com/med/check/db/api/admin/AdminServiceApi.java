package com.med.check.db.api.admin;

import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/service")
@CrossOrigin(origins = "*", maxAge = 3000)
@RequiredArgsConstructor
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
