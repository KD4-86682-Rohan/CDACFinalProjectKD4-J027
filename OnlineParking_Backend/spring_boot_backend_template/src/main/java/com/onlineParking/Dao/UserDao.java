package com.onlineParking.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.User;

public interface UserDao extends JpaRepository<User, Long> {

}
