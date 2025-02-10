package com.onlineParking.CustomException;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String errMesg)
	{
		super(errMesg);
	}
}
