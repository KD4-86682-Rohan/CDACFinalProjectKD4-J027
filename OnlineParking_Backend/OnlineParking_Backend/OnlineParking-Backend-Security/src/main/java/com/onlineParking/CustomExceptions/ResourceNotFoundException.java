package com.onlineParking.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String errMesg) {
		super(errMesg);
	}
	

}
