package com.onlineParking.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.User;

import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.models.responses.ApiResponse;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	

	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByEmail(String email) {
		User dbUser = userDao.findByEmail(email).orElseThrow(()-> new ApiException("NO User Found"));
		return dbUser;
	}
	
	@Override
	public User getUserByCredentials(UserAuthDto auth) {
		User dbUser = userDao.findByEmail(auth.getEmail()).orElseThrow(()-> new ApiException("NO User Found"));
		if(dbUser != null && dbUser.getPassword().equals(auth.getPassword()))
			return dbUser;
		return null;
	}

	@Override
	public String registerUser(User user){
	    try {
	        // Check if email already exists
	        if (userDao.existsByEmail(user.getEmail())) {
	            return "Email already exists!";
	        }

	        
	        userDao.save(user);

	        return "User registered successfully!";
	    } catch (IOException e) {
	        throw new RuntimeException("Error saving profile photo", e);
	    }
	}

	@Override
	public User loginUser(UserAuthDto user) {
		return userDao.findByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
	}
	

}
