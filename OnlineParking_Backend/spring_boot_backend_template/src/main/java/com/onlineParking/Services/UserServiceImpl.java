package com.onlineParking.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.CustomException.ApiException;
import com.onlineParking.DTO.UserAuthDto;
import com.onlineParking.DTO.UserReqDto;
import com.onlineParking.DTO.UserRespDto;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String RegisterUser(UserReqDto user) {
		User u=modelMapper.map(user, User.class);
		User persistentUser = userDao.save(u);
		return "Added New Category";
	}

	@Override
	public UserRespDto LoginUser(UserAuthDto user) {
		User u = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword())
				.orElseThrow(()->
				new ApiException("Invallid Email or Password"));
		
		return modelMapper.map(user, UserRespDto.class);
	}

}
