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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.BookingReqDto;
import com.onlineParking.DTO.BookingRespDto;
import com.onlineParking.DTO.VendorBookingDto;
import com.onlineParking.Services.BookingService;

@RestController
@RequestMapping("/Booking")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class BookingContoller {
	
	@Autowired
	private BookingService bookingService;
	
	
	@GetMapping("/getAllBooking/{vendorId}")
	public ResponseEntity<?> getAllBookings(@PathVariable Long vendorId) {
		List<BookingRespDto> bookings = bookingService.getAllBookings(vendorId);
		if(bookings.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.ok(bookings);
	}
	
	
	//add booking
	@PostMapping("/addBooking/{userId}")
	public ResponseEntity<?> addBooking(@PathVariable Long userId,@RequestBody VendorBookingDto vendorIds, @RequestBody BookingReqDto dto){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(bookingService.addBooking(userId, vendorIds, dto));
	}
	
	
	@PutMapping("/extendBooking/{userId}")
	public ResponseEntity<?> extendBooking(@PathVariable Long userId, @RequestParam Long slotId,@RequestBody BookingReqDto dto){
		return ResponseEntity.ok(bookingService.extendBooking(userId, slotId, dto));
	}
	
	@DeleteMapping("/cancelBooking/{userId}")
	public ResponseEntity<?> cancelBooking(@PathVariable Long userId,@RequestParam Long slotId){
		try {
			return ResponseEntity.ok(bookingService.cancelBooking(userId, slotId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	

}
