package com.onlineParking.Pojos;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Audit_Log")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class AuditLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false) // Foreign Key to User(Admin)
    private User admin;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String description;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
