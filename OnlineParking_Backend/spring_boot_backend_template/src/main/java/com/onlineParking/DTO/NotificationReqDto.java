package com.onlineParking.DTO;

import com.onlineParking.Pojos.NotificationType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationReqDto {

	private String message;

    private NotificationType notificationType;

    private Boolean isRead;
}
