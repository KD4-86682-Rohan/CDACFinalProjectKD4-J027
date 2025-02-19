package com.onlineParking.Pojos;

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

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;

}

