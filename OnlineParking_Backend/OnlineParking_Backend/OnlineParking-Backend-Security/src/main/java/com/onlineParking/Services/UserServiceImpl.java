package com.onlineParking.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.User;
import com.onlineParking.Security.UserDetail;

import io.jsonwebtoken.io.IOException;
@Service
@Transactional
public class UserServiceImpl implements UserService,UserDetailsService {
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
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
	    public UserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
	        User user = userDao.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return UserDetail.buildUserDetails(user);
	    }

	@Override
	public String registerUser(User user){
	    try {
	        // Check if email already exists
	        if (userDao.existsByEmail(user.getEmail())) {
	            return "Email already exists!";
	        }

	        // Encode the password
	        user.setPassword(passwordEncoder.encode(user.getPassword()));

	        // Save the user to the database
	        userDao.save(user);

	        return "User registered successfully!";
	    } catch (IOException e) {
	        throw new RuntimeException("Error saving profile photo", e);
	    }
	}






	@Override
	public User loginUser(String email) {
		return userDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
	

}
