package com.onlineParking.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class RevenueRespDto {
	
	private Double totalRevenue;
	private Double totalRevenue2W;
	private Double totalRevenue4W;
	private Double vendorEarning;
	private Double adminEarning;
	
}
