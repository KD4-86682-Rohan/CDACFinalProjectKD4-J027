package com.onlineParking.Pojos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class User extends BaseEntity implements UserDetails{
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
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(this.role.name());
		return authorities;
	}

	@Override
	public String getUsername() {
		
		return this.email;
	}
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == true;
    }
    
}
