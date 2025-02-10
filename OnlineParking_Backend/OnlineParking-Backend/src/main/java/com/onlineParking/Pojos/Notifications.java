package com.onlineParking.Pojos;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Notifications")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Notifications extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign Key to User
    private User user;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    private NotificationType notificationType;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

//    @Column(name = "created_at", nullable = false)
//    private LocalDateTime createdAt;
}

//enum NotificationType {
//    BOOKING_CONFIRMATION, PAYMENT_SUCCESS, SLOT_REMINDER, SYSTEM_ALERT
//}
