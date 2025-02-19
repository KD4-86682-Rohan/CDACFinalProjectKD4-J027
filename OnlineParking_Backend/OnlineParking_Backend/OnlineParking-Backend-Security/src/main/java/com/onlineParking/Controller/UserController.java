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
import com.onlineParking.Security.JwtUtil;
import com.onlineParking.Services.UserService;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class UserController {
	
	@Autowired
	private UserService  userService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody UserAuthDto cr) {
		// authenticate user with authentication manager		
		Authentication auth = new UsernamePasswordAuthenticationToken(cr.getEmail(), cr.getPassword());
		System.out.println("BEFORE AUTH: " + auth);
		auth = authManager.authenticate(auth);
		System.out.println("AFTER AUTH: " + auth);
		// after authentication, create JWT token and return.
		String token = jwtUtil.generateJwtTokenForUser(auth);
		return ResponseEntity.ok(token);
	}
	
	
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
		        System.out.println("Login attempt: " + cr.getEmail());

		        Authentication auth = authManager.authenticate(
		            new UsernamePasswordAuthenticationToken(cr.getEmail(), cr.getPassword())
		        );

		        System.out.println("Authentication successful for: " + cr.getEmail());
		        System.out.println("Auth principal class: " + auth.getPrincipal().getClass().getName());

		        if (!(auth.getPrincipal() instanceof User)) {
		            throw new RuntimeException("Principal is not an instance of User!");
		        }
		        System.out.println("Auth . get principal"+auth.getPrincipal());
		        User user = (User) auth.getPrincipal(); // Cast to User
		        System.out.println("user = "+user);
		        String token = jwtUtil.generateJwtTokenForUser(auth);
		        return ResponseEntity.ok(new LoginResponse(user.getId(),token, "Login successful", user.getRole(), true));
		    } catch (Exception e) {
		        System.out.println("Authentication failed: " + e.getMessage());
		        return ResponseEntity.status(401).body(new ApiResponse("Invalid credentials"));
		    }
	}

}
