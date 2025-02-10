package com.onlineParking.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRespDto {
    private Long reviewId;
    private int rating;
    private String reviewText;
    private Long userId;
    private Long locationId;
}
