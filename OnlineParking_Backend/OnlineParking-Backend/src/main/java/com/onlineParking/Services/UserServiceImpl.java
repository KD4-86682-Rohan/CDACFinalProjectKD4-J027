package com.onlineParking.Services;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.DTO.UserReqDto;
import com.onlineParking.DTO.UserRespDto;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.User;
import com.onlineParking.Security.JwtUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<?> RegisterUser(UserReqDto user) {
		
		if (userDao.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already registered.");
        }
		
		User u = modelMapper.map(user, User.class);
//		User persistentUser = userDao.save(u);
//		return "New user Registred";
		
		u.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userDao.save(u);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

//	@Override
//	public ResponseEntity<?> LoginUser(UserAuthDto user) {
//
//	    Optional<User> userOpt = userDao.findByEmail(user.getEmail());
//
//	    if (userOpt.isEmpty()) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
//	    }
//
//	    User u = userOpt.get();
//
//	    // Validate password
//	    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
//	    }
//
//	    // Authentication successful, map user to UserRespDto
//	    UserRespDto userResponse = modelMapper.map(u, UserRespDto.class);
//
//	    return ResponseEntity.ok(userResponse);
//	}
	
	@Override
	public ResponseEntity<?> LoginUser(UserAuthDto user) {
	    Optional<User> userOpt = userDao.findByEmail(user.getEmail());

	    if (userOpt.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("error", "Invalid credentials", "message", "User not found"));
	    }

	    User u = userOpt.get();

	    // Validate password
	    if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("error", "Invalid credentials", "message", "Incorrect password"));
	    }

	    // Generate JWT Token
	    String token = jwtUtil.generateToken(u.getEmail());

	    // Authentication successful, map user to UserRespDto
	    UserRespDto userResponse = modelMapper.map(u, UserRespDto.class);

	    // Return JSON response with token, role, and user details
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "Login successful");
	    response.put("token", token); // JWT token for authentication
	    response.put("role", u.getRole()); // Ensure role is included
	    response.put("user", userResponse);

	    return ResponseEntity.ok(response);
	}


	
	@Override
	public ResponseEntity<?> updateUser(Long userId, UserReqDto updatedUser) {
        Optional<User> existingUserOpt = userDao.findById(userId);

        if (existingUserOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        User existingUser = existingUserOpt.get();

        // Update only non-null fields
        if (updatedUser.getFirstName() != null) existingUser.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null) existingUser.setLastName(updatedUser.getLastName());
        if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPhone() != null) existingUser.setPhone(updatedUser.getPhone());
        if (updatedUser.getGender() != null) existingUser.setGender(updatedUser.getGender());
        if (updatedUser.getDob() != null) existingUser.setDob(updatedUser.getDob());
        if (updatedUser.getLicenseNumber() != null) existingUser.setLicenseNumber(updatedUser.getLicenseNumber());

        userDao.save(existingUser);

        return ResponseEntity.ok(existingUser);
    }


}
