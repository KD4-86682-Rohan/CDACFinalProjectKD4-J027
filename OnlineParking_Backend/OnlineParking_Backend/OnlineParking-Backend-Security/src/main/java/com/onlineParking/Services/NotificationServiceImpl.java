package com.onlineParking.Services;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.NotificationRespDto;
import com.onlineParking.Dao.NotificationDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.User;


@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<NotificationRespDto> getNotificationByUserId(Long userId) {
		User user = userDao.findById(userId).orElseThrow(()-> new ApiException("User not found"));
		
		return notificationDao.findByUser(user)
				.stream()
				.map(notifications->modelMapper.map(notifications, NotificationRespDto.class))
				.collect(Collectors.toList());
	}
	

}
