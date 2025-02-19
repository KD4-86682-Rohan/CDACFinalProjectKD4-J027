package com.onlineParking.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.DateTimeDto;
import com.onlineParking.DTO.ParkingSlotReqDto;
import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.ParkingSlotDao;
import com.onlineParking.Dao.SlotAvailabilityDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.SlotAvailability;

@Service
@Transactional
public class ParkingSlotServiceImpl implements ParkingSlotService {
	@Autowired
	private ParkingSlotDao parkingSlotDao;

	@Autowired
	private ParkingLocationDao parkingLocationDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SlotAvailabilityDao availabilityDao;

	@Override
	public List<ParkingSlotRespDto> getAllParkingSlots(Long id) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(id)
				.orElseThrow(() -> new ApiException("Invallid Location Id"));

		if(!parkingLocation.isStatus())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found");
		}
		return parkingSlotDao.findByLocation(parkingLocation) 
				.stream() 
				.filter(parkingSlot -> parkingSlot.isStatus())
				.map(parkingSlots -> modelMapper.map(parkingSlots, ParkingSlotRespDto.class)) 
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addNewParkingSlot(Long lId, ParkingSlotReqDto dto) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(lId)
				.orElseThrow(() -> new RuntimeException("Invalid Id"));
		if(!parkingLocation.isStatus())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found");
		}
		ParkingSlots parkingSlots = modelMapper.map(dto, ParkingSlots.class);
		parkingSlots.setLocation(parkingLocation);
		parkingSlotDao.save(parkingSlots);
		return new ApiResponse("New Parking slot added");
	}

	@Override
	public ApiResponse updateParkingSlot(Long id, ParkingSlotReqDto dto) {
		ParkingSlots parkingSlots = parkingSlotDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id"));
		parkingSlots.setPricePerHour2W(dto.getPricePerHour2W());
		parkingSlots.setPricePerHour4W(dto.getPricePerHour4W());
		parkingSlots.setSlotStatus(dto.getSlotStatus());
		parkingSlots.setSlotType(dto.getSlotType());
		parkingSlotDao.save(parkingSlots);
		return new ApiResponse("Parking Slot Updated");
	}

	@Override
	public ApiResponse deleteParkingSlot(Long id) {
		ParkingSlots parkingSlots = parkingSlotDao.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id"));
		parkingSlots.setStatus(false);
		parkingSlotDao.save(parkingSlots);
		return new ApiResponse("Parking Slot Deleted");
	}

	@Override
	public List<ParkingSlotRespDto> getAvailableParkingSlots(Long locationId, DateTimeDto dto) {
	    ParkingLocation parkingLocation = parkingLocationDao.findById(locationId)
	            .orElseThrow(() -> new ApiException("Invalid Location Id"));

	    if (!parkingLocation.isStatus()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location is not active.");
	    }

	    List<ParkingSlots> allSlots = parkingSlotDao.findByLocation(parkingLocation);

	    List<ParkingSlots> availableSlots = allSlots.stream()
	            .filter(slot -> isSlotAvailable(slot, dto.getDate(), dto.getStartTime(), dto.getEndTime()))
	            .collect(Collectors.toList());

	    return availableSlots.stream()
	            .map(slot -> modelMapper.map(slot, ParkingSlotRespDto.class))
	            .collect(Collectors.toList());
	}

	// Helper method to check slot availability
	private boolean isSlotAvailable(ParkingSlots slot, LocalDate date, LocalDateTime startTime, LocalDateTime endTime) {
	    List<SlotAvailability> existingBookings = availabilityDao.findBySlotAndDate(slot, date);

	    // If no bookings exist for this slot on this date, it's available
	    if (existingBookings.isEmpty()) {
	        return true;
	    }

	    for (SlotAvailability booking : existingBookings) {
	        if (booking.getIsBooked()) {
	            if (!(endTime.isBefore(booking.getStartTime()) || startTime.isAfter(booking.getEndTime()))) {
	                return false; 
	            }
	        }
	    }
	    return true; 
	}


}
