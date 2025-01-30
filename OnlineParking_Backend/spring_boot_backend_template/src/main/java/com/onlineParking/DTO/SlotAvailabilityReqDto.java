package com.onlineParking.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlotAvailabilityReqDto {

	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Boolean isBooked;
}
