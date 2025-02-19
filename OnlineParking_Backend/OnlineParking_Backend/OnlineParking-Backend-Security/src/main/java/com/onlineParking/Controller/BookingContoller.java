package com.onlineParking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.BookingReqDto;
import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Services.BookingService;

@RestController
@RequestMapping("/Booking")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class BookingContoller {
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<?> getBookingsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getAllBookingsByUserId(userId));
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity<?> getBookingsByVendorId(@PathVariable Long vendorId) {
        return ResponseEntity.ok(bookingService.getAllBookingsByVendorId(vendorId));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<?> getBookingsByVendorAndLocation(
            @PathVariable Long vendorId, @RequestParam Long locationId) {
        return ResponseEntity.ok(bookingService.getAllBookingsByVendorAndLocation(vendorId, locationId));
    }
	

	@PostMapping("/addBooking/{userId}")
	public ResponseEntity<?> bookSlot(@PathVariable Long userId, @RequestParam Long slotId, @RequestBody BookingReqDto dto) {
	    try {
	        Bookings booking = bookingService.createBooking(userId, slotId, dto);
	        return ResponseEntity.ok("Booking successful! Booking ID: " + booking.getId());
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage()));
	    }
	}

	
	@DeleteMapping("/cancelBooking/{userId}")
	public ResponseEntity<?> cancelBooking(@PathVariable Long userId, @RequestParam Long bookingId) {
	    try {
	        return ResponseEntity.ok(bookingService.cancelBooking(userId, bookingId));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
	    }
	}

	

}
