package com.onlineParking.Services;

import org.springframework.http.ResponseEntity;
import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.DTO.UserReqDto;

public interface UserService {
	ResponseEntity<?> RegisterUser(UserReqDto user);
	ResponseEntity<?> LoginUser(UserAuthDto user); 
    ResponseEntity<?> updateUser(Long uId, UserReqDto user);
}
