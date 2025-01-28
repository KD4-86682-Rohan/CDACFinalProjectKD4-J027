package com.onlineParking.Pojos;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Parking_Location")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ParkingLocation extends BaseEntity {
	private String city;
	private String area;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false) // Foreign Key Column
    private User vendor;
}
