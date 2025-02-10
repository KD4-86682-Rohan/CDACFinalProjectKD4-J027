package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.RevenueRespDto;

public interface RevenueService {
	List<RevenueRespDto> getAllRevenueByLocation(Long locationId);

	Double findByLocationAndVehicleType(Long locatioId, String vehicleType);
	
	Double findByLocationIdAndVendorId(Long locationId, Long vendorId);
}
