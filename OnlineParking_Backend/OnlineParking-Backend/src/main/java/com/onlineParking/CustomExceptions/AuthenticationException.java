package com.onlineParking.CustomExceptions;

public class AuthenticationException extends RuntimeException{
	public AuthenticationException(String errMesg) {
		super(errMesg);
	}

}
