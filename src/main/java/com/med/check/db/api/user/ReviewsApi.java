package com.med.check.db.api.user;

import com.med.check.db.dto.request.ApplicationRequest;
import com.med.check.db.dto.request.ReviewRequest;
import com.med.check.db.dto.response.ReviewsResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.service.ApplicationService;
import com.med.check.db.service.ReviewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Reviews And Application API")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewsApi {

    private final ReviewsService reviewsService;
    private final ApplicationService applicationService;

    @GetMapping("/get-reviews")
    @Operation(summary = "Get reviews method",
            description = "This method gets all reviews")
    @PreAuthorize("hasAuthority('PATIENT')")
    public List<ReviewsResponse> getReviews(){
        return reviewsService.getAllReviews();
    }

    @PostMapping("/add-review")
    @Operation(summary = "Post review method",
            description = "This method adds review to database")
    @PreAuthorize("hasAuthority('PATIENT')")
    public SimpleResponse addReview(@RequestBody @Valid ReviewRequest request){
        return reviewsService.addReview(request);
    }

    @PostMapping("/add-application")
    @Operation(summary = "Add application method",
            description = "This method add application")
    public SimpleResponse addApplication(@RequestBody @Valid ApplicationRequest request){
        return applicationService.addApplication(request);
    }
}
