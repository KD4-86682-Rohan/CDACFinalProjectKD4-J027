package com.onlineParking.Controller;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ReviewReqDto;
import com.onlineParking.DTO.ReviewRespDto;
import com.onlineParking.Services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private ReviewService reviewService;

    // Add a new review
    @PostMapping("/add/{userId}/{locationId}")
    public ApiResponse addReview(
            @PathVariable Long userId, 
            @PathVariable Long locationId, 
            @RequestBody ReviewReqDto reviewReqDto) {
        return reviewService.addReview(userId, locationId, reviewReqDto);
    }

    // Edit an existing review
    @PutMapping("/edit/{reviewId}")
    public ApiResponse editReview(
            @PathVariable Long reviewId, 
            @RequestBody ReviewReqDto reviewReqDto) {
        return reviewService.editReview(reviewId, reviewReqDto);
    }

    // Soft delete a review
    @DeleteMapping("/delete/{reviewId}")
    public ApiResponse softDeleteReview(@PathVariable Long reviewId) {
        return reviewService.softDeleteReview(reviewId);
    }

    // Get all reviews
    @GetMapping("/all")
    public List<ReviewRespDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Get reviews by location
    @GetMapping("/location/{locationId}")
    public List<ReviewRespDto> getReviewsByLocation(@PathVariable Long locationId) {
        return reviewService.getReviewsByLocation(locationId);
    }
}
