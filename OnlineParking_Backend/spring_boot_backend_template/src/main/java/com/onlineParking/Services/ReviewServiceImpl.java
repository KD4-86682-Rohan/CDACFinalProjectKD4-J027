package com.onlineParking.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ReviewReqDto;
import com.onlineParking.DTO.ReviewRespDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.ReviewDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.Reviews;
import com.onlineParking.Pojos.User;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ParkingLocationDao parkingLocationDao;

    @Autowired
    private ModelMapper modelMapper;

    // Add a new review
    @Override
    public ApiResponse addReview(ReviewReqDto dto, Long userId, Long locationId) {
        User user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("Invalid User Id"));
        ParkingLocation location = parkingLocationDao.findById(locationId).orElseThrow(() -> new RuntimeException("Invalid Location Id"));
        
        Reviews review = modelMapper.map(dto, Reviews.class);
        review.setUser(user);
        review.setLocation(location);
        
        Reviews savedReview = reviewDao.save(review);
        return new ApiResponse("Review added successfully with ID = " + savedReview.getId());
    }

    // Edit an existing review
    @Override
    public ApiResponse editReview(Long reviewId, ReviewReqDto dto, Long userId) {
        Reviews review = reviewDao.findById(reviewId).orElseThrow(() -> new RuntimeException("Invalid Review Id"));
        
        if (!review.getUser().getId().equals(userId)) {
            return new ApiResponse("You can only edit your own reviews");
        }

        review.setRating(dto.getRating());
        review.setReviewText(dto.getReviewText());
        reviewDao.save(review);
        
        return new ApiResponse("Review updated successfully");
    }

    // Soft delete a review
    @Override
    public ApiResponse deleteReview(Long reviewId, Long userId) {
        Reviews review = reviewDao.findById(reviewId).orElseThrow(() -> new RuntimeException("Invalid Review Id"));

        if (!review.getUser().getId().equals(userId)) {
            return new ApiResponse("You can only delete your own reviews");
        }

        review.setDeleted(true); // Assuming a `deleted` boolean column in the Review entity for soft deletion
        reviewDao.save(review);

        return new ApiResponse("Review deleted successfully");
    }

    // Get all reviews
    @Override
    public List<ReviewRespDto> getAllReviews() {
        return reviewDao.findAllByDeletedFalse().stream()
                .map(review -> modelMapper.map(review, ReviewRespDto.class))
                .collect(Collectors.toList());
    }

    // Get all reviews for a specific location
    @Override
    public List<ReviewRespDto> getReviewsByLocation(Long locationId) {
        return reviewDao.findByLocationIdAndDeletedFalse(locationId).stream()
                .map(review -> modelMapper.map(review, ReviewRespDto.class))
                .collect(Collectors.toList());
    }
}
