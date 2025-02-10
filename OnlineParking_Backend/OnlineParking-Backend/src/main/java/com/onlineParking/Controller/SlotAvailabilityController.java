package com.onlineParking.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.DTO.SlotAvailabilityReqDto;
import com.onlineParking.Services.SlotAvailabilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Availability")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class SlotAvailabilityController {
	@Autowired
	private SlotAvailabilityService slotAvailabilityService;
	
	@PostMapping("/addAvailability/{sId}")
	public ResponseEntity<?> addSlotAvailability(@PathVariable Long sId,@Valid @RequestBody SlotAvailabilityReqDto dto)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(slotAvailabilityService.addNewSlotAvailability(sId, dto));
	}
	
	@GetMapping("/availabilityByDate/{lId}")
	public ResponseEntity<?> checkAvailabilityByDate(@PathVariable Long lId, @RequestParam("date") LocalDate date) {
	    List<ParkingSlotRespDto> slots = slotAvailabilityService.findByDate(lId, date);
	    if (slots.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
	    return ResponseEntity.ok(slots);
	}
	
	@GetMapping("/availabilityBySlot/{lId}")
	public ResponseEntity<?> checkAvailabilityBySlot(@PathVariable Long lId)
	{
		List<ParkingSlotRespDto> slots = slotAvailabilityService.findByParkingSlot(lId);
		if(slots.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(slots);
	}
}
