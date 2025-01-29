package com.onlineParking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.ParkingLocationReqDto;
import com.onlineParking.DTO.ParkingLocationRespDto;
import com.onlineParking.Services.ParkingLocationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/ParkingLocation")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class ParkingLocationController {

	@Autowired
	private ParkingLocationService parkingLocationService;
	
	@GetMapping
	public ResponseEntity<?> getParkingLocations() {
//		System.out.println("get all");
		List<ParkingLocationRespDto> locations = parkingLocationService.getAllParkingLocations();
		if (locations.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(locations);
	}
	
	@PostMapping("/{vendor_id}")
	public ResponseEntity<?> addNewParkingLocation(@PathVariable Long vendor_id,@RequestBody ParkingLocationReqDto location) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(parkingLocationService.addNewParkingLocation(location, vendor_id));
	}
	
	
}
