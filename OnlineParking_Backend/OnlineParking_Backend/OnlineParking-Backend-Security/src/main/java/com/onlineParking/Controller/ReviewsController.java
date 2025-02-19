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
    @PostMapping("/add/{userId}")
    public ApiResponse addReview(
            @PathVariable Long userId, 
            @RequestParam Long locationId, 
            @RequestBody ReviewReqDto reviewReqDto) {
        return reviewService.addReview(userId, locationId, reviewReqDto);
    }

    // Edit an existing review
    @PutMapping("/edit/{uId}")
    public ApiResponse editReview(
            @RequestParam Long reviewId,
            @PathVariable Long uId,
            @RequestBody ReviewReqDto reviewReqDto) {
        return reviewService.editReview(reviewId, uId, reviewReqDto);
    }

    // Soft delete a review
    @DeleteMapping("/delete/{uId}")
    public ApiResponse softDeleteReview(@RequestParam Long reviewId, @PathVariable Long uId) {
        return reviewService.softDeleteReview(reviewId, uId);
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
