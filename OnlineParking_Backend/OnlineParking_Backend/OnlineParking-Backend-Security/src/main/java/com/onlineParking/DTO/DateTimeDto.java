package com.onlineParking.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateTimeDto {

	LocalDate date;
	LocalDateTime startTime;
	LocalDateTime endTime;
}
