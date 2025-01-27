package com.onlineParking.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.User;

public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public String RegisterUser(User user) {
		User persistentUser= userDao.save(user);
		return "Added New Category";
	}

	@Override
	public User LoginUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
