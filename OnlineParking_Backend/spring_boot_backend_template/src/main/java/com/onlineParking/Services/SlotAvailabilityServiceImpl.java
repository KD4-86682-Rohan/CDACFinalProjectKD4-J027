package com.onlineParking.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.DTO.SlotAvailabilityReqDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.ParkingSlotDao;
import com.onlineParking.Dao.SlotAvailabilityDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.SlotAvailability;

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
	public List<ParkingSlotRespDto> findByDate(Long lId, LocalDate date) {
	    ParkingLocation parkingLocation = locationDao.findById(lId)
	            .orElseThrow(() -> new RuntimeException("Invalid Location Id"));

	    List<ParkingSlotRespDto> availableSlots = parkingSlotDao.findByLocation(parkingLocation).stream()
	            .filter(slot -> slot.isStatus() && isSlotAvailable(slot, date))
	            .map(slot -> modelMapper.map(slot, ParkingSlotRespDto.class))
	            .collect(Collectors.toList());

	    return availableSlots;
	}


	// Helper method to check if the slot is available on the given date

	private boolean isSlotAvailable(ParkingSlots slot, LocalDate date) {
	    return availabilityDao.findBySlotAndDateAndIsBookedFalse(slot, date).isEmpty();
	}
	

	@Override
	public List<ParkingSlotRespDto> findByParkingSlot(Long lId) {
	    ParkingLocation parkingLocation = locationDao.findById(lId)
	            .orElseThrow(() -> new RuntimeException("Invalid Location Id"));

	    List<ParkingSlotRespDto> availableSlots = parkingSlotDao.findByLocation(parkingLocation).stream()
	            .filter(slot -> slot.isStatus() && isSlotAvailable(slot))
	            .map(slot -> modelMapper.map(slot, ParkingSlotRespDto.class))
	            .collect(Collectors.toList());

	    return availableSlots;
	}


	// Helper method to check if the slot is available
	private boolean isSlotAvailable(ParkingSlots slot) {
	    return !availabilityDao.findBySlotAndIsBookedFalse(slot).isEmpty();
	}

//	@Override
//	public List<ParkingSlotRespDto> findByParkingSlot(Long lId) {
//	    ParkingLocation parkingLocation = locationDao.findById(lId)
//	            .orElseThrow(() -> new RuntimeException("Invalid Location Id"));
//
//	    List<SlotAvailability> availableSlots = availabilityDao.findByIsBookedFalse();
//	    System.out.println("All available slots: " + availableSlots);
//
//	    if (availableSlots.isEmpty()) {
//	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No slots available.");
//	    }
//
//	    List<ParkingSlotRespDto> parkingSlots = availableSlots.stream()
//	            .map(slotAvailability -> modelMapper.map(slotAvailability.getSlot(), ParkingSlotRespDto.class))
//	            .collect(Collectors.toList());
//
//	    System.out.println("Total slots returned: " + parkingSlots.size());
//	    return parkingSlots;
//	}


	@Override
	public ApiResponse addNewSlotAvailability(Long sId, SlotAvailabilityReqDto dto) {
		ParkingSlots parkingSlots = parkingSlotDao.findById(sId)
				.orElseThrow(() -> new RuntimeException("Invalid Id"));
		if(!parkingSlots.isStatus())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Slot found");

		}
		SlotAvailability slotAvailability = modelMapper.map(dto, SlotAvailability.class);
		slotAvailability.setSlot(parkingSlots);
		availabilityDao.save(slotAvailability);
		return new ApiResponse("New Parking slot Availability added");
	}

	
}
