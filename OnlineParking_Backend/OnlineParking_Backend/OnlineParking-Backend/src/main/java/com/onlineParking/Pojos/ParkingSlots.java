package com.onlineParking.Pojos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Parking_Slots")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ParkingSlots extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false) // Foreign Key to ParkingLocation
    private ParkingLocation location;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vendor_id", nullable = false)
//    private User vendor;

    @Column(name = "slot_number", nullable = false)
    private String slotNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "slot_type", nullable = false)
    private SlotType slotType;
    
    @Column(name = "price_per_hour_2w")
    private Double pricePerHour2W;
    
    @Column(name = "price_per_hour_4w")
    private Double pricePerHour4W;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SlotStatus slotStatus;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean status;

	@PrePersist
	public void prePersist() {
		this.status = true;
	}
}
