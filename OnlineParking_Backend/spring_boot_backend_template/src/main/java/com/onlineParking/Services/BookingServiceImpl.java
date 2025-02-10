package com.onlineParking.Services;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.onlineParking.CustomException.ApiException;
import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.BookingReqDto;
import com.onlineParking.DTO.BookingRespDto;
import com.onlineParking.DTO.VendorBookingDto;
import com.onlineParking.Dao.BookingDao;
import com.onlineParking.Dao.ParkingSlotDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.Role;
import com.onlineParking.Pojos.SlotType;
import com.onlineParking.Pojos.User;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private ParkingSlotDao parkingSlotDao;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public List<BookingRespDto> getAllBookingsBYVendor(Long vendorId) {
		User vendor = userDao.findById(vendorId).orElseThrow(()->new ApiException("Invalid ID"));
		if(!vendor.getRole().equals(Role.Vendor)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not a Vendor");
		} 
		return bookingDao.findByVendor(vendor).stream().map(bookings->modelMapper.map(bookings, BookingRespDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<BookingRespDto> getAllBookings(Long userId) {
		User user = userDao.findById(userId).orElseThrow(()->new ApiException("Invalid ID"));
		if(!user.getRole().equals(Role.User)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not a user");
		} 
		return bookingDao.findByUser(user).stream().map(bookings->modelMapper.map(bookings, BookingRespDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse addBooking(Long userId, VendorBookingDto vendorIds, BookingReqDto dto) {
		User user = userDao.findById(userId).orElseThrow(() -> new ApiException("Invalid Id"));
		User vendor = userDao.findById(vendorIds.getVendorId()).orElseThrow(() -> new ApiException("Invalid Id"));
		ParkingSlots slots = parkingSlotDao.findById(vendorIds.getSlotId()).orElseThrow(()->new ApiException("Invalid slot"));
		SlotType s= slots.getSlotType();
		double p;
		if(s.equals(SlotType.FOUR_WHEELER))
		{
			p=slots.getPricePerHour4W();
		}else {
			p=slots.getPricePerHour2W();
		}
		if(user.getRole().equals(Role.User)) {
			Bookings booking = modelMapper.map(dto, Bookings.class);
			booking.setUser(user);
			booking.setVendor(vendor);
			booking.setSlot(slots);
			Duration duration = Duration.between(dto.getEndTime(), dto.getStartTime());
			double hours=duration.toHours();
			double price = hours*p;
			booking.setTotalPrice(Math.abs(price));
			Bookings persistentBooking = bookingDao.save(booking);
//			return new ApiResponse("Booking done"+ slots.getId());
			return new ApiResponse("Booking confirmed! Slot ID: " + slots.getId() + ", Booking ID: " + persistentBooking.getId());
		}
		return new ApiResponse("Booking not done");
	}

	@Override
	public ApiResponse cancelBooking(Long userId, Long slotId) {
		User user = userDao.findById(userId).orElseThrow(()->new ApiException("Invalid User Id"));
		if(user.getRole().equals(Role.User)) {
			Bookings bookings = bookingDao.findById(slotId)
					.orElseThrow(()->new ApiException("Invalid slot Id"));
			bookings.setBookingStatus(false);
			bookingDao.save(bookings);
			return new ApiResponse("Booking cancelled");
		}
		return new ApiResponse("booking not cancelled");
	}

	
	//update booking
	@Override
	public ApiResponse extendBooking(Long userId, Long slotId, BookingReqDto dto) {
		User user = userDao.findById(userId).orElseThrow(()->new ApiException("Invalid userId"));
		ParkingSlots slots = parkingSlotDao.findById(slotId).orElseThrow(()->new ApiException("Invalid slot"));
		SlotType s= slots.getSlotType();
		double p;
		if(s.equals(SlotType.FOUR_WHEELER))
		{
			p=slots.getPricePerHour4W();
		}else {
			p=slots.getPricePerHour2W();
		}
		if(user.getRole().equals(Role.User)) {
			Bookings bookings = bookingDao.findById(slotId)
					.orElseThrow(()->new ApiException("Invalid slot Id"));
			bookings.setEndTime(dto.getEndTime());
			Duration duration = Duration.between(dto.getEndTime(), dto.getStartTime());
			double hours=duration.toHours();
			double price = hours*p;
			bookings.setTotalPrice(Math.abs(price));
			bookingDao.save(bookings);
			return new ApiResponse("parking time extended");
		}
		return new ApiResponse("parking time not extended");
	}
	

}
