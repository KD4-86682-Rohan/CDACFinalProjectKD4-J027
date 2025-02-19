package com.onlineParking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.ParkingLocationReqDto;
import com.onlineParking.DTO.ParkingLocationRespDto;
import com.onlineParking.Services.ParkingLocationService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ParkingLocation")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class ParkingLocationController {

	@Autowired
	private ParkingLocationService parkingLocationService;

	@GetMapping()
	public ResponseEntity<?> getParkingLocations() {
//		System.out.println("get all");
		List<ParkingLocationRespDto> locations = parkingLocationService.getAllParkingLocations();
		if (locations.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(locations);
	}

	@GetMapping("/search")
	public ResponseEntity<List<ParkingLocationRespDto>> searchParkingLocations(
	        @RequestParam(required = false) String city,
	        @RequestParam(required = false) String area) {

	    List<ParkingLocationRespDto> locations = parkingLocationService.searchParkingLocations(city, area);
	    
	    if (locations.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
	    return ResponseEntity.ok(locations);
	}
	
	@PostMapping("/addLocation/{vendor_id}")
	public ResponseEntity<?> addNewParkingLocation(@PathVariable Long vendor_id,
			@RequestBody ParkingLocationReqDto location) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(parkingLocationService.addNewParkingLocation(location, vendor_id));
	}

	@DeleteMapping("/deleteLoaction/{id}/{vId}")
	public ResponseEntity<?> deleteLocation(@PathVariable Long id, @PathVariable Long vId) {
		try {
			// invoke service layer method
			return ResponseEntity.ok(parkingLocationService.deleteParkingLocation(id, vId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}

	@PutMapping("/updateLocation/{id}/{vId}")
	public ResponseEntity<?> updateLocation(@PathVariable Long id,@PathVariable Long vId, @RequestBody ParkingLocationReqDto location) {
		return ResponseEntity.ok(parkingLocationService.updateParkingLocation(location, id ,vId));
	}

}
