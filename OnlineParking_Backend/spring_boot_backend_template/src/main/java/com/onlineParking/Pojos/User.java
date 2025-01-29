package com.onlineParking.Pojos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class User extends BaseEntity{
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String email;
	private String password;
	@Column(name="phone_number")
	private String phone;
	private Gender gender;
	private Date dob;
	private Role role;
	@Column(name="license_number")
	private String LicenseNumber;
	
}
