package com.med.check.db.service;

import com.med.check.db.dto.request.ReviewRequest;
import com.med.check.db.dto.response.ReviewsResponse;
import com.med.check.db.dto.response.SimpleResponse;

import java.util.List;

public interface ReviewsService {
    List<ReviewsResponse> getAllReviews();

    SimpleResponse addReview(ReviewRequest request);
}
