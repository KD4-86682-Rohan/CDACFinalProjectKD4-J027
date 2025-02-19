package com.onlineParking.Services;

import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.Pojos.User;

public interface UserService {
	User getUserByEmail(String email);

	User getUserByCredentials(UserAuthDto auth);

	String registerUser(User user);

	User loginUser(UserAuthDto user);
}
