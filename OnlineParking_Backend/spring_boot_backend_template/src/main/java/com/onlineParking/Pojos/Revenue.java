package com.onlineParking.Pojos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Revenue")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Revenue extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false) // Foreign Key to ParkingLocation
    private ParkingLocation location;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // Foreign Key to user
    private User user;

    @Column(name = "total_revenue", nullable = false)
    private Double totalRevenue;

    @Column(name = "total_revenue_2w", nullable = false)
    private Double totalRevenue2W;

    @Column(name = "total_revenue_4w", nullable = false)
    private Double totalRevenue4W;

    @Column(name = "vendor_earning", nullable = false)
    private Double vendorEarning;

    @Column(name = "admin_earning", nullable = false)
    private Double adminEarning;

//    @Column(name = "updated_at", nullable = false)
//    private LocalDateTime updatedAt;
}
