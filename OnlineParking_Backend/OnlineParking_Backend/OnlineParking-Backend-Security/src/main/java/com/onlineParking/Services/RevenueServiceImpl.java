package com.onlineParking.Services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.RevenueRespDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.RevenueDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.Revenue;
import com.onlineParking.Pojos.SlotType;

@Service
@Transactional
public class RevenueServiceImpl implements RevenueService {
	
	@Autowired
	private RevenueDao revenueDao;
	
	@Autowired
	private ParkingLocationDao parkingLocatinDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public RevenueRespDto getAllRevenueByLocation(Long locationId) {
	    
	    ParkingLocation parkingLocation = parkingLocatinDao.findById(locationId)
	            .orElseThrow(() -> new ApiException("Invalid Location"));
	    
	    if (!parkingLocation.isStatus()) {
	        throw new ApiException("Location is inactive or not found");
	    }

	    Revenue revenue = revenueDao.findByLocation(parkingLocation);

	    if (revenue == null) {
	        throw new ApiException("No revenue data available for this location");
	    }

	    return modelMapper.map(revenue, RevenueRespDto.class);
	}


	@Override
	public Double findByLocationAndVehicleType(Long locatioId, String vehicleType) {
		ParkingLocation parkingLocation = parkingLocatinDao.findById(locatioId)
				.orElseThrow(()->new ApiException("Invalid Location id"));
		
		Revenue revenue = revenueDao.findByLocation(parkingLocation);
		
		if(revenue==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no location found");
		}
		
		double totalRevenue = 0.0;

		if (vehicleType.equals(SlotType.TWO_WHEELER)) {
	        totalRevenue = revenue.getTotalRevenue2W();
	    } else if (vehicleType.equals(SlotType.FOUR_WHEELER)) {
	        totalRevenue = revenue.getTotalRevenue4W();
	    } else {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid vehicle type. Use '2W' or '4W'.");
	    }

	    return totalRevenue;
	
	}

	@Override
	public Double findByLocationIdAndVendorId(Long locationId, Long vendorId) {
			
		return null;
	}

}
