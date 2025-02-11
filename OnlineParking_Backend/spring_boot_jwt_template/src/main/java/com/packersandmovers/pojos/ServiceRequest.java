package com.packersandmovers.pojos;

import java.sql.Date;
import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_requests")
@NoArgsConstructor
@Getter
@Setter
public class ServiceRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "VendorID", nullable = false)
    private Vendor vendor;
    
    @ManyToOne
    @JoinColumn(name = "ShiftingID", nullable = false)
    private ShiftingType shiftingType;
    
    @Column(nullable = false)
    private String pickupLocation;
    
    @Column(nullable = false)
    private String dropoffLocation;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date preferredDate;
    
    @Column(columnDefinition = "TEXT")
    private String additionalRequirements;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus requestStatus = RequestStatus.PENDING;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;
    
    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(nullable = false)
    private double shipmentWeight; 
}
