package com.onlineParking.Pojos;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class User extends BaseEntity {//implements UserDetails{
	@Column(name = "first_name", length = 50)
	private String firstName;
	@Column(name = "last_name", length = 50)
	private String lastName;
	@Column(length = 100)
	private String email;
	private String password;
	@Column(name = "phone_number", length = 10)
	private String phone;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private Date dob;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(name = "license_number", length = 20)
	private String licenseNumber;
	private boolean status;

	@PrePersist
	public void prePersist() {
		this.status = true;
	}
	
//	 @Override
//	    public Collection<? extends GrantedAuthority> getAuthorities() {
//	        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
//	    }
//	 
//	@Override
//	public String getUsername() {
//		return this.email;
//	}
//	@Override
//	public String getPassword() {
//		return password;
//	}

}
