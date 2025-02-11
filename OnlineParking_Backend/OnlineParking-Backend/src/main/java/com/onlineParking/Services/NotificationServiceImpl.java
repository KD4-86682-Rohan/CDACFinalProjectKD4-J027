package com.onlineParking.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.onlineParking.CustomException.ApiException;
import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.NotificationReqDto;
import com.onlineParking.DTO.NotificationRespDto;
import com.onlineParking.Dao.NotificationDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.Notifications;
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
	public ApiResponse addNotification(Long userId, NotificationReqDto dto) {
		User user = userDao.findById(userId).orElseThrow(()->new ApiException("Invalid Id"));
		Notifications notification = modelMapper.map(dto, Notifications.class);
		notification.setUser(user);
		notificationDao.save(notification);
		return new ApiResponse("Notification Added Successfully");
	}




	@Override
	public List<NotificationRespDto> getNotificationByUserId(Long userId) {
//		User user = userDao.findById(userId).orElseThrow(()->new ApiException("Invalid Id"));
//		Optional<Notifications> notificationList = notificationDao.findById(userId);
		
		return notificationDao.findById(userId)
				.stream()
				.map(notifications->modelMapper.map(notifications, NotificationRespDto.class))
				.collect(Collectors.toList());
	}
	

}
