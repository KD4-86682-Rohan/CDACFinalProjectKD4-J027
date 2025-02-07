package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.BookingReqDto;
import com.onlineParking.DTO.BookingRespDto;
import com.onlineParking.DTO.VendorBookingDto;


public interface BookingService {
	List<BookingRespDto> getAllBookings(Long userId);
	List<BookingRespDto> getAllBookingsBYVendor(Long userId);
	ApiResponse addBooking(Long userId, VendorBookingDto vendorIds, BookingReqDto dto);
	ApiResponse cancelBooking(Long userId, Long slotId);
	ApiResponse extendBooking(Long userId, Long slotId, BookingReqDto dto); //update
}
