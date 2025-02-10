package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.NotificationReqDto;
import com.onlineParking.DTO.NotificationRespDto;

public interface NotificationService {
	List<NotificationRespDto> getNotificationByUserId(Long userId);
	ApiResponse addNotification(Long userId, NotificationReqDto dto);
}
