package com.onlineParking.DTO;

import java.time.LocalDateTime;

import com.onlineParking.Pojos.BookingStatus;
import com.onlineParking.Pojos.SlotType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class BookingRespDto {


	    private SlotType slotType;

	    private LocalDateTime bookingTime;

	    private LocalDateTime startTime;

	    private LocalDateTime endTime;

	    private BookingStatus status;

	    private Double totalPrice;
	    
	    private boolean BookingStatus;
}
