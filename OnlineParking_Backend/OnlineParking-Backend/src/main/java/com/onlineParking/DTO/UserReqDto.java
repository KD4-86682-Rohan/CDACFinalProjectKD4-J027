package com.onlineParking.DTO;

import java.sql.Date;

import com.onlineParking.Pojos.Gender;
import com.onlineParking.Pojos.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReqDto {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private Gender gender;
	private Date dob;
	private Role role;
	private String licenseNumber;
}
