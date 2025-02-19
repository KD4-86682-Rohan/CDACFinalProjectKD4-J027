package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.BookingReqDto;
import com.onlineParking.DTO.BookingRespDto;
import com.onlineParking.Pojos.Bookings;

public interface BookingService {
	
	Bookings createBooking(Long userId, Long slotId, BookingReqDto dto);
	ApiResponse cancelBooking(Long bookingId, Long userId);
	List<BookingRespDto> getAllBookingsByUserId(Long userId);
	List<BookingRespDto> getAllBookingsByVendorId(Long vendorId);
	List<BookingRespDto> getAllBookingsByVendorAndLocation(Long vendorId, Long locationId);
}
