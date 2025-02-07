package com.onlineParking.Pojos;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Reviews")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Reviews extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign Key to User
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false) // Foreign Key to ParkingLocation
    private ParkingLocation location;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "review_text")
    private String reviewText;

//    @Column(name = "created_at", nullable = false)
//    private LocalDateTime createdAt;
    
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false; // Default: Not deleted
}
