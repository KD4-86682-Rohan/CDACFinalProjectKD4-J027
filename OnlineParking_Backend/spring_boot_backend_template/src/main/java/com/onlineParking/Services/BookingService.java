package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.BookingReqDto;
import com.onlineParking.DTO.BookingRespDto;


public interface BookingService {
	List<BookingRespDto> getAllBookings(Long vendorId);
	ApiResponse addBooking(Long userId, Long slotId, BookingReqDto dto);
	ApiResponse cancelBooking(Long userId, Long slotId);
	ApiResponse extendBooking(Long userId, Long slotId, BookingReqDto dto); //update
}
