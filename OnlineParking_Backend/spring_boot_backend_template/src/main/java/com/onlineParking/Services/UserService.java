package com.onlineParking.Services;

import com.onlineParking.Pojos.User;

public interface UserService {
	String RegisterUser(User user);
	User LoginUser(String email, String password); 
}
