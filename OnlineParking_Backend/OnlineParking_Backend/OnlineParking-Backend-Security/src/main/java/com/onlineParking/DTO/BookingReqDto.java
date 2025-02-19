package com.onlineParking.DTO;

import java.time.LocalDateTime;

import com.onlineParking.Pojos.SlotType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingReqDto {
	
	    private SlotType slotType;

	    private LocalDateTime bookingTime;

	    private LocalDateTime startTime;

	    private LocalDateTime endTime;

//	    private BookingStatus status;

//	    private Double totalPrice;
	    
//	    private boolean BookingStatus;
}
