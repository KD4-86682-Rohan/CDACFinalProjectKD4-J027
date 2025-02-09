package com.onlineParking.Services;

import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.DTO.UserReqDto;
import com.onlineParking.DTO.UserRespDto;
import com.onlineParking.Pojos.User;

public interface UserService {
	String RegisterUser(UserReqDto user);
	UserRespDto LoginUser(UserAuthDto user); 
}
