package com.onlineParking.Services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.ParkingSlotDao;
import com.onlineParking.Dao.SlotAvailabilityDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.ParkingSlots;

@Service
@Transactional
public class SlotAvailabilityServiceImpl implements SlotAvailabilityService {

	@Autowired
	private ParkingLocationDao locationDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ParkingSlotDao parkingSlotDao;
	
	@Autowired
	private SlotAvailabilityDao availabilityDao;
	
	@Override
	public List<ParkingSlotRespDto> findByDate(Long lId, Date date) {
	    ParkingLocation parkingLocation = locationDao.findById(lId)
	            .orElseThrow(() -> new RuntimeException("Invalid Location Id"));

	    List<ParkingSlotRespDto> availableSlots = parkingSlotDao.findByLocation(parkingLocation)
	            .stream()
	            .filter(slot -> slot.isStatus() && isSlotAvailable(slot, date)) 
	            .map(slot -> modelMapper.map(slot, ParkingSlotRespDto.class)) 
	            .collect(Collectors.toList());

	    return availableSlots;
	}

	// Helper method to check if the slot is available on the given date
	private boolean isSlotAvailable(ParkingSlots slot, Date date) {
	    return availabilityDao.findBySlotAndDateAndIsBookedFalse(slot, date) == null; 
	}

	@Override
	public List<ParkingSlotRespDto> findByParkingSlot(Long lId) {
		ParkingLocation parkingLocation = locationDao.findById(lId)
	            .orElseThrow(() -> new RuntimeException("Invalid Location Id"));
		
		List<ParkingSlotRespDto> availableSlots = parkingSlotDao.findByLocation(parkingLocation)
	            .stream()
	            .filter(slot -> slot.isStatus() && isSlotAvailable(slot)) 
	            .map(slot -> modelMapper.map(slot, ParkingSlotRespDto.class)) 
	            .collect(Collectors.toList());
		return availableSlots;
	}

	
	// Helper method to check if the slot is available
		private boolean isSlotAvailable(ParkingSlots slot) {
		    return availabilityDao.findBySlotAndIsBookedFalse(slot) == null; 
		}
	
}
