package com.onlineParking.DTO;

import com.onlineParking.Pojos.SlotStatus;
import com.onlineParking.Pojos.SlotType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ParkingSlotRespDto {

	private String slotNumber;
    private SlotType slotType;
    private Double pricePerHour2W;
    private Double pricePerHour4W;
    private SlotStatus status;
}
