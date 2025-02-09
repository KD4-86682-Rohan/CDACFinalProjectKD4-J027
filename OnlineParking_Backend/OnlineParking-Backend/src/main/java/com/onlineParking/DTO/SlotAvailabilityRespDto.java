package com.onlineParking.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class SlotAvailabilityRespDto {

	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Boolean isBooked;
}
