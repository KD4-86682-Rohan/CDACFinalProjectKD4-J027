package com.onlineParking.DTO;



import com.onlineParking.Pojos.NotificationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class NotificationRespDto {

    private String message;

    private NotificationType notificationType;

    private Boolean isRead;
}
