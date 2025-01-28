package com.onlineParking.DTO;

import java.sql.Date;

import com.onlineParking.Pojos.Gender;
import com.onlineParking.Pojos.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class UserRespDto {
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Gender gender;
	private Date dob;
	private Role role;
	private String LicenseNumber;
}
