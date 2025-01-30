package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ReviewReqDto;
import com.onlineParking.DTO.ReviewRespDto;

public interface ReviewService {
    
    ApiResponse addReview(ReviewReqDto dto, Long userId, Long locationId);
    
    ApiResponse editReview(Long reviewId, ReviewReqDto dto, Long userId);
    
    ApiResponse deleteReview(Long reviewId, Long userId);
    
    List<ReviewRespDto> getAllReviews();
    
    List<ReviewRespDto> getReviewsByLocation(Long locationId);
}
