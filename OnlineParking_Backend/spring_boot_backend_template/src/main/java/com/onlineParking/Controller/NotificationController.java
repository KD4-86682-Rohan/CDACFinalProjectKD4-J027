package com.onlineParking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.NotificationRespDto;
import com.onlineParking.Services.NotificationService;

@RestController
@RequestMapping("/Notification")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/getNotification/{userId}")
	public ResponseEntity<?> getNotificationByUserId(@PathVariable Long userId) {
		List<NotificationRespDto> notification = notificationService.getNotificationByUserId(userId);
		if(notification.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(notification);
	}
	

}
