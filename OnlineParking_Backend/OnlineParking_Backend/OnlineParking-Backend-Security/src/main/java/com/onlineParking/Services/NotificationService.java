package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.NotificationRespDto;

public interface NotificationService {
	List<NotificationRespDto> getNotificationByUserId(Long userId);
}
