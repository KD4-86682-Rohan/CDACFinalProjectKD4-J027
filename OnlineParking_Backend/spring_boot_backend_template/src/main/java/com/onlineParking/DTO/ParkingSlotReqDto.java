package com.onlineParking.DTO;

import com.onlineParking.Pojos.SlotStatus;
import com.onlineParking.Pojos.SlotType;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSlotReqDto {
	@NotBlank(message = "Slot Number cannot be blank")
    private String slotNumber;
    private SlotType slotType;
    private Double pricePerHour2W;
    private Double pricePerHour4W;
    private SlotStatus slotStatus;
}
