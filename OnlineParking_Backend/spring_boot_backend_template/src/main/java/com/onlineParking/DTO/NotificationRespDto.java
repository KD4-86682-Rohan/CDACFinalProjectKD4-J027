package com.onlineParking.DTO;



import com.onlineParking.Pojos.NotificationType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
