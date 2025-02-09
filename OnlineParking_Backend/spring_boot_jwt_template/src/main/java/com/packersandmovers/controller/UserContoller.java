package com.packersandmovers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.AuthRequest;
import com.packersandmovers.dto.SigninResponse;
import com.packersandmovers.dto.UserReqDTO;
import com.packersandmovers.security.JwtUtils;
import com.packersandmovers.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/users")
public class UserContoller 
{
	@Autowired
	UserService userService;
	
	@Autowired
    private AuthenticationManager authMgr;

    @Autowired
    private JwtUtils jwtUtils;
	 
		@PostMapping("/signup")
		public String userSignIn(@RequestBody UserReqDTO user)
		{
			return userService.signUp(user);
		}
		
//		@PostMapping("/signin")
//		public ResponseEntity<?> userSignIn(@RequestBody AuthRequest dto)
//		{
//			System.out.println("in user sign in "+dto);
//			try {
//				return ResponseEntity.ok(userService.signIn(dto));
//			} catch (RuntimeException e) {
//				//SC 401 , err mesg
//				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//						.body(new ApiResponse(e.getMessage()));
//			}
//		}
		
		@PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@RequestBody @Valid AuthRequest request) {
	        System.out.println("Attempting sign-in: " + request.getEmail());

	        try {
	            // Step 1: Authenticate user credentials
	            Authentication authToken = authMgr.authenticate(
	                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	            );

	            // Step 2: Set security context
	            SecurityContextHolder.getContext().setAuthentication(authToken);

	            // Step 3: Generate JWT token
	            String jwtToken = jwtUtils.generateJwtToken(authToken);

	            // Step 4: Return authentication response
	            SigninResponse response = new SigninResponse(jwtToken, "Authentication Successful!");
	            return ResponseEntity.status(HttpStatus.CREATED).body(response);

	        } catch (Exception e) {
	            System.out.println("Authentication failed: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                    .body(new ApiResponse("Invalid email or password!"));
	        }
		}
}
