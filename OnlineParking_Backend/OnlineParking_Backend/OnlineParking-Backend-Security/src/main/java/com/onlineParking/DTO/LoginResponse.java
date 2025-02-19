package com.onlineParking.DTO;

import com.onlineParking.Pojos.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
	private long id;
    private String token;
    private String message;
    private Role role;
    private boolean success;


}
