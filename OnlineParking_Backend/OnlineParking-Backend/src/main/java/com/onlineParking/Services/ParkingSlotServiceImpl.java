package com.onlineParking.Services;

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
import com.onlineParking.DTO.ParkingSlotReqDto;
import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.ParkingSlotDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.SlotStatus;
import com.onlineParking.Pojos.SlotType;

@Service
@Transactional
public class ParkingSlotServiceImpl implements ParkingSlotService {
	@Autowired
	private ParkingSlotDao parkingSlotDao;

	@Autowired
	private ParkingLocationDao parkingLocationDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ParkingSlotRespDto> getAllParkingSlots(Long id) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(id)
				.orElseThrow(() -> new ApiException("Invallid Location Id"));
//	    List<ParkingSlots> parkingSlots = parkingSlotDao.findByLocation(parkingLocation);

		if(!parkingLocation.isStatus())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found");
		}
		return parkingSlotDao.findByLocation(parkingLocation) // List<CategoryEnt>
				.stream() // Stream<Entity>
				.filter(parkingSlot -> parkingSlot.isStatus())
				.map(parkingSlots -> modelMapper.map(parkingSlots, ParkingSlotRespDto.class)) // Stream<DTO>
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
	public List<ParkingSlotRespDto> getAllBySlotStatus(Long lId, SlotStatus status) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(lId)
				.orElseThrow(() -> new ApiException("Invallid Location Id"));
		if(!parkingLocation.isStatus())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found");
		}
		return parkingSlotDao.findBySlotStatus(status) // List<CategoryEnt>
				.stream() // Stream<Entity>
				.filter(parkingSlot -> parkingSlot.isStatus() && parkingSlot.getLocation().equals(parkingLocation))
				.map(parkingSlots -> modelMapper.map(parkingSlots, ParkingSlotRespDto.class)) // Stream<DTO>
				.collect(Collectors.toList());
//		return null;
	}

	@Override
	public List<ParkingSlotRespDto> getAllBySlotType(Long lId, SlotType type) {
		ParkingLocation parkingLocation = parkingLocationDao.findById(lId)
				.orElseThrow(() -> new ApiException("Invallid Location Id"));
		if(!parkingLocation.isStatus())
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found");
		}
		return parkingSlotDao.findBySlotType(type) // List<CategoryEnt>
				.stream() // Stream<Entity>
				.filter(parkingSlot -> parkingSlot.isStatus() && parkingSlot.getLocation().equals(parkingLocation))
				.map(parkingSlots -> modelMapper.map(parkingSlots, ParkingSlotRespDto.class)) // Stream<DTO>
				.collect(Collectors.toList());
	}

}
