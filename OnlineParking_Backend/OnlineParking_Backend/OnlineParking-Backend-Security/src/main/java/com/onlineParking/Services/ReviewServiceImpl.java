package com.onlineParking.Services;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ReviewReqDto;
import com.onlineParking.DTO.ReviewRespDto;
import com.onlineParking.Dao.ReviewDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Pojos.Reviews;
import com.onlineParking.Pojos.User;
import com.onlineParking.Pojos.ParkingLocation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ApiResponse addReview(Long userId, Long locationId, ReviewReqDto reviewReqDto) {
        User user = userDao.findById(userId).orElseThrow(() -> new ApiException("User not found"));
        ParkingLocation location = parkingLocationDao.findById(locationId).orElseThrow(() -> new ApiException("Location not found"));

        Reviews review = new Reviews();
        review.setUser(user);
        review.setLocation(location);
        review.setRating(reviewReqDto.getRating());
        review.setReviewText(reviewReqDto.getReviewText());
        review.setDeleted(false);

        reviewDao.save(review);
        return new ApiResponse("Review added successfully");
    }

    @Override
    public ApiResponse editReview(Long reviewId,Long userId, ReviewReqDto reviewReqDto) {
    	User user= userDao.findById(userId).orElseThrow(()-> new ApiException("User not found"));
        Reviews review = reviewDao.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
    	if(review.getUser()==user) {
    		review.setRating(reviewReqDto.getRating());
            review.setReviewText(reviewReqDto.getReviewText());

            reviewDao.save(review);
    	}else {
    		return new ApiResponse("User can Update only his Review");
    	}
        
        return new ApiResponse("Review updated successfully");
    }

    @Override
    public ApiResponse softDeleteReview(Long userId, Long reviewId) {
    	User user= userDao.findById(userId).orElseThrow(()-> new ApiException("User not found"));
        Reviews review = reviewDao.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
        if(review.getUser()==user) {
        	review.setDeleted(true);
            reviewDao.save(review);
            return new ApiResponse("Review soft deleted successfully");
    	}else {
    		return new ApiResponse("User can Delete only his Review");
    	}
        
    }

    @Override
    public List<ReviewRespDto> getAllReviews() {
        return reviewDao.findAllByDeletedFalse().stream()
                .map(review -> modelMapper.map(review, ReviewRespDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewRespDto> getReviewsByLocation(Long locationId) {
        return reviewDao.findByLocationIdAndDeletedFalse(locationId).stream()
                .map(review -> modelMapper.map(review, ReviewRespDto.class))
                .collect(Collectors.toList());
    }
}
