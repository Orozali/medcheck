package com.med.check.db.api.admin;

import com.med.check.db.dto.request.AnalysisCategoryRequest;
import com.med.check.db.dto.request.AnalysisRequest;
import com.med.check.db.dto.response.GetAnalysisCategoryResponse;
import com.med.check.db.dto.response.GetAnalysisResponse;
import com.med.check.db.dto.response.SimpleResponse;
import com.med.check.db.service.AnalysisCategoryService;
import com.med.check.db.service.AnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/analysis")
@RequiredArgsConstructor
@Tag(name = "Admin Analysis API")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('ADMIN')")
public class AdminAnalysisApi {

    private final AnalysisService analysisService;
    private final AnalysisCategoryService analysisCategoryService;

    @GetMapping("/get-analysisCategory")
    @Operation(summary = "Get all analysis category",
            description = "This method gets all analysis categories and shows in admin page")
    public List<GetAnalysisCategoryResponse> getAllAnalysisCategory(){
        return analysisCategoryService.getAll();
    }

    @PostMapping("/add-analysisCategory")
    @Operation(summary = "Add analysis category",
            description = "This method adds analysis category to the database")
    public SimpleResponse addAnalysisCategory(@RequestBody AnalysisCategoryRequest request){
        return analysisCategoryService.add(request);
    }

    @DeleteMapping("/delete-analysisCategory/{id}")
    @Operation(summary = "Delete analysis category",
            description = "This method deletes analysis category from the database")
    public SimpleResponse deleteAnalysisCategory(@PathVariable("id") Long analysisCategory_id){
        return analysisCategoryService.delete(analysisCategory_id);
    }

    @GetMapping("/get-analysis-by-categoryId/{id}")
    public List<GetAnalysisResponse> getAnalysisByCategoryId(@PathVariable("id") Long category_id){
        return analysisService.getAllAnalysisByCategoryId(category_id);
    }

    @GetMapping("/get-analysis-by-Id/{id}")
    public GetAnalysisResponse getAnalysisById(@PathVariable("id") Long id){
        return analysisService.getAnalysisById(id);
    }

    @DeleteMapping("/delete-analysis/{id}")
    public SimpleResponse deleteAnalysisById(@PathVariable("id") Long analysis_id){
        return analysisService.deleteById(analysis_id);
    }

    @PutMapping("/edit-analysis/{id}")
    public SimpleResponse editAnalysisById(@RequestBody AnalysisRequest request){
        return analysisService.editAnalysis(request);
    }

}
