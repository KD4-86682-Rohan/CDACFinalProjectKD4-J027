package com.onlineParking.Services;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ReviewReqDto;
import com.onlineParking.DTO.ReviewRespDto;
import java.util.List;

public interface ReviewService {
    
    ApiResponse addReview(Long userId, Long locationId, ReviewReqDto reviewReqDto);
    
    ApiResponse editReview(Long reviewId,Long userId, ReviewReqDto reviewReqDto);
    
    ApiResponse softDeleteReview(Long userId, Long reviewId);
    
    List<ReviewRespDto> getAllReviews();
    
    List<ReviewRespDto> getReviewsByLocation(Long locationId);
}
