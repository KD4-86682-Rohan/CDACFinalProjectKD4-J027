package com.onlineParking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.ParkingSlotRespDto;
import com.onlineParking.DTO.RevenueRespDto;
import com.onlineParking.Dao.RevenueDao;
import com.onlineParking.Services.RevenueService;

@RestController
@RequestMapping("/Revenue")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class RevenueController {
	
	@Autowired
	private RevenueService revenueService;
	
	@GetMapping("/getAll/{id}")
	public ResponseEntity<?> getRevenueByLocation(@PathVariable Long id){
		List<RevenueRespDto> revenue = revenueService.getAllRevenueByLocation(id);
		if(revenue.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(revenue);
	}
	
	
	
	
}
