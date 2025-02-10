package com.onlineParking.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class ParkingLocationRespDto extends BaseDto {
	private String city;
	private String area;
}
