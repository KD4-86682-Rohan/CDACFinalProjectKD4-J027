package com.onlineParking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.DTO.UserReqDto;
import com.onlineParking.Services.UserService;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody UserAuthDto userAuth)
	{	
		try {
			return ResponseEntity.ok(userService.LoginUser(userAuth));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
	@PostMapping("/register")
	public ResponseEntity<?> Register(@RequestBody UserReqDto user)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.RegisterUser(user));
	}
	

}
