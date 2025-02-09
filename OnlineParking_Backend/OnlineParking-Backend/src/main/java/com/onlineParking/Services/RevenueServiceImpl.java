package com.onlineParking.Services;

import java.util.List;

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
	public List<RevenueRespDto> getAllRevenueByLocation(Long locationId) {
		ParkingLocation parkingLocation = parkingLocatinDao.findById(locationId)
				.orElseThrow(()-> new ApiException("Invalid Location"));
		if(!parkingLocation.isStatus())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found");
		}
		
		List<Revenue> revenueList = revenueDao.findByLocation(parkingLocation);
		
		return revenueList.stream()
				.map(revenue-> modelMapper.map(revenue, RevenueRespDto.class))
				.toList();
				
	}

	@Override
	public Double findByLocationAndVehicleType(Long locatioId, String vehicleType) {
		ParkingLocation parkingLocation = parkingLocatinDao.findById(locatioId)
				.orElseThrow(()->new ApiException("Invalid Location id"));
		
		List<Revenue> revenueList = revenueDao.findByLocation(parkingLocation);
		
		if(revenueList.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no location found");
		}
		
		double totalRevenue = 0.0;

		if (vehicleType.equalsIgnoreCase("2W")) {
	        totalRevenue = revenueList.stream().mapToDouble(Revenue::getTotalRevenue2W).sum();
	    } else if (vehicleType.equalsIgnoreCase("4W")) {
	        totalRevenue = revenueList.stream().mapToDouble(Revenue::getTotalRevenue4W).sum();
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
