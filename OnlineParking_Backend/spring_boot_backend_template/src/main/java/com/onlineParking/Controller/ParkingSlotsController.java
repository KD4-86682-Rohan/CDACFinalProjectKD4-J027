package com.onlineParking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ParkingSlotReqDto;
import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.Pojos.SlotStatus;
import com.onlineParking.Pojos.SlotType;
import com.onlineParking.Services.ParkingSlotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/User")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class ParkingSlotsController {
	@Autowired
	private ParkingSlotService parkingSlotService;
	
	@GetMapping("/getAll/{id}")
	public ResponseEntity<?> getParkingSlots(@PathVariable Long id){
		List<ParkingSlotRespDto> slots = parkingSlotService.getAllParkingSlots(id);
		if(slots.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(slots);
	}
	
	@GetMapping("/getByStatus/{lId}/{slotStatus}")
	public ResponseEntity<?> getParkingSlotsBySlotStatus(@PathVariable Long lId, @PathVariable SlotStatus slotStatus)
	{
		List<ParkingSlotRespDto> slots = parkingSlotService.getAllBySlotStatus(lId, slotStatus);
		if(slots.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(slots);
	}
	
	@GetMapping("/getByType/{lId}/{slotType}")
	public ResponseEntity<?> getParkingSlotBySlotType(@PathVariable Long lId, @PathVariable SlotType type)
	{
		List<ParkingSlotRespDto> slots = parkingSlotService.getAllBySlotType(lId, type);
		if(slots.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(slots);
	}
	
	@PostMapping("/addSlot/{lId}")
	public ResponseEntity<?> addSlot(@PathVariable Long lId,@Valid @RequestBody ParkingSlotReqDto dto)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(parkingSlotService.addNewParkingSlot(lId, dto));
	}
	
	@DeleteMapping("/deleteSlot/{id}")
	public ResponseEntity<?> deleteSlot(@PathVariable Long id)
	{
		try {
			// invoke service layer method
			return ResponseEntity.ok(parkingSlotService.deleteParkingSlot(id));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/updateSlot/{id}")
	public ResponseEntity<?> updateSlot(@PathVariable Long id, @RequestBody ParkingSlotReqDto dto)
	{
		return ResponseEntity.ok(parkingSlotService.updateParkingSlot(id, dto));
	}
}
