package com.onlineParking.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onlineParking.Pojos.User;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail implements UserDetails {
    private Long id;
    private  String email;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public static UserDetail buildUserDetails(User user) {
        // Assuming getRole() returns a single Enum, not a collection
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        return new UserDetail(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority) // Wrapping in a list
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}