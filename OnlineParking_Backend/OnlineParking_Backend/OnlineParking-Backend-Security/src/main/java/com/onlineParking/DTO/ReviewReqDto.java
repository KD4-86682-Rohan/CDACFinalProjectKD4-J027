package com.onlineParking.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewReqDto {
    private int rating;
    private String reviewText;
}
