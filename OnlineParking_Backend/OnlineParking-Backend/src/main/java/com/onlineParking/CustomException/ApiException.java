package com.onlineParking.CustomException;

public class ApiException extends RuntimeException{
	public ApiException(String errMesg)
	{
		super(errMesg);
	}
}
