package com.onlineParking.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.User;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByEmail(String username);
}
