package com.onlineParking.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.LoginResponse;
import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.Pojos.User;
import com.onlineParking.Services.UserService;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class UserController {
	
	@Autowired
	private UserService  userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(
	        @RequestBody User user) {
		try{
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful!");

        }catch (ApiException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserAuthDto cr) {
		 try {
		        return ResponseEntity.ok(userService.loginUser(cr));
		    } catch (Exception e) {
		        System.out.println("Authentication failed: " + e.getMessage());
		        return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials"));
		    }
	}

}
