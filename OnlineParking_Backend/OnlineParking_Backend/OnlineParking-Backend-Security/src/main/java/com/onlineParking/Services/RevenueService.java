package com.onlineParking.Services;


import com.onlineParking.DTO.RevenueRespDto;

public interface RevenueService {
	RevenueRespDto getAllRevenueByLocation(Long locationId);

	Double findByLocationAndVehicleType(Long locatioId, String vehicleType);
	
	Double findByLocationIdAndVendorId(Long locationId, Long vendorId);
}
