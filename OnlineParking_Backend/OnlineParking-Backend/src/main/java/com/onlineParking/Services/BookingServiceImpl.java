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
import com.onlineParking.Dao.RevenueDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.Revenue;
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
	private RevenueDao revenueDao; 
	
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
	    ParkingSlots slot = parkingSlotDao.findById(vendorIds.getSlotId()).orElseThrow(() -> new ApiException("Invalid slot"));

	    SlotType slotType = slot.getSlotType();
	    double pricePerHour = slotType.equals(SlotType.FOUR_WHEELER) ? slot.getPricePerHour4W() : slot.getPricePerHour2W();

	    // Calculate total price
	    Duration duration = Duration.between(dto.getStartTime(), dto.getEndTime());
	    double hours = duration.toHours();
	    double totalPrice = Math.abs(hours * pricePerHour);

	    // Save Booking
	    Bookings booking = modelMapper.map(dto, Bookings.class);
	    booking.setUser(user);
	    booking.setVendor(vendor);
	    booking.setSlot(slot);
	    booking.setTotalPrice(totalPrice);
	    Bookings savedBooking = bookingDao.save(booking);

	    // Update Revenue
	    ParkingLocation location = slot.getLocation();
	    Revenue revenue = revenueDao.findByLocation(location)
	                                .stream()
	                                .findFirst()  // Assuming one revenue per location
	                                .orElseGet(() -> {
	                                    Revenue newRevenue = new Revenue();
	                                    newRevenue.setLocation(location);
	                                    newRevenue.setUser(vendor);
	                                    newRevenue.setTotalRevenue(0.0);
	                                    newRevenue.setTotalRevenue2W(0.0);
	                                    newRevenue.setTotalRevenue4W(0.0);
	                                    newRevenue.setVendorEarning(0.0);
	                                    newRevenue.setAdminEarning(0.0);
	                                    return revenueDao.save(newRevenue);
	                                });

	    revenue.setTotalRevenue(revenue.getTotalRevenue() + totalPrice);
	    if (slotType.equals(SlotType.FOUR_WHEELER)) {
	        revenue.setTotalRevenue4W(revenue.getTotalRevenue4W() + totalPrice);
	    } else {
	        revenue.setTotalRevenue2W(revenue.getTotalRevenue2W() + totalPrice);
	    }

	    // Assuming vendor gets 80% and admin gets 20%
	    double vendorEarning = totalPrice * 0.8;
	    double adminEarning = totalPrice * 0.2;
	    revenue.setVendorEarning(revenue.getVendorEarning() + vendorEarning);
	    revenue.setAdminEarning(revenue.getAdminEarning() + adminEarning);

	    revenueDao.save(revenue);

	    return new ApiResponse("Booking confirmed! Slot ID: " + slot.getId() + ", Booking ID: " + savedBooking.getId());
	}


	@Override
	public ApiResponse cancelBooking(Long userId, Long bookingId) {
	    User user = userDao.findById(userId).orElseThrow(() -> new ApiException("Invalid User Id"));
	    if (!user.getRole().equals(Role.User)) {
	        return new ApiResponse("Only users can cancel bookings");
	    }

	    Bookings booking = bookingDao.findById(bookingId)
	            .orElseThrow(() -> new ApiException("Invalid booking Id"));

	    // Reverse Revenue
	    ParkingSlots slot = booking.getSlot();
	    ParkingLocation location = slot.getLocation();
	    Revenue revenue = revenueDao.findByLocation(location)
	                                .stream()
	                                .findFirst()
	                                .orElseThrow(() -> new ApiException("Revenue entry not found"));

	    double refundAmount = booking.getTotalPrice();
	    revenue.setTotalRevenue(revenue.getTotalRevenue() - refundAmount);

	    if (slot.getSlotType().equals(SlotType.FOUR_WHEELER)) {
	        revenue.setTotalRevenue4W(revenue.getTotalRevenue4W() - refundAmount);
	    } else {
	        revenue.setTotalRevenue2W(revenue.getTotalRevenue2W() - refundAmount);
	    }

	    // Revert earnings
	    double vendorEarning = refundAmount * 0.8;
	    double adminEarning = refundAmount * 0.2;
	    revenue.setVendorEarning(revenue.getVendorEarning() - vendorEarning);
	    revenue.setAdminEarning(revenue.getAdminEarning() - adminEarning);

	    revenueDao.save(revenue);

	    // Mark booking as canceled
	    booking.setBookingStatus(false);
	    bookingDao.save(booking);

	    return new ApiResponse("Booking cancelled and revenue updated.");
	}


	
	//update booking
	@Override
	public ApiResponse extendBooking(Long userId, Long bookingId, BookingReqDto dto) {
	    User user = userDao.findById(userId).orElseThrow(() -> new ApiException("Invalid userId"));
	    Bookings booking = bookingDao.findById(bookingId)
	            .orElseThrow(() -> new ApiException("Invalid booking Id"));

	    if (!booking.getUser().getId().equals(userId)) {
	        throw new ApiException("Unauthorized: You can only extend your own bookings.");
	    }

	    ParkingSlots slot = booking.getSlot();
	    ParkingLocation location = slot.getLocation();

	    double pricePerHour = (slot.getSlotType().equals(SlotType.FOUR_WHEELER)) 
	                            ? slot.getPricePerHour4W() 
	                            : slot.getPricePerHour2W();

	    // Calculate additional duration and price
	    Duration additionalDuration = Duration.between(booking.getEndTime(), dto.getEndTime());
	    double additionalHours = additionalDuration.toHours();
	    if (additionalHours <= 0) {
	        throw new ApiException("Invalid extension: End time must be later than the current end time.");
	    }

	    double additionalPrice = Math.abs(additionalHours * pricePerHour);
	    
	    // Update booking
	    booking.setEndTime(dto.getEndTime());
	    booking.setTotalPrice(booking.getTotalPrice() + additionalPrice);
	    bookingDao.save(booking);

	    // Update Revenue
	    Revenue revenue = revenueDao.findByLocation(location)
	                                .stream()
	                                .findFirst()
	                                .orElseGet(() -> {
	                                    Revenue newRevenue = new Revenue();
	                                    newRevenue.setLocation(location);
	                                    newRevenue.setUser(booking.getVendor());
	                                    newRevenue.setTotalRevenue(0.0);
	                                    newRevenue.setTotalRevenue2W(0.0);
	                                    newRevenue.setTotalRevenue4W(0.0);
	                                    newRevenue.setVendorEarning(0.0);
	                                    newRevenue.setAdminEarning(0.0);
	                                    return revenueDao.save(newRevenue);
	                                });

	    revenue.setTotalRevenue(revenue.getTotalRevenue() + additionalPrice);
	    
	    if (slot.getSlotType().equals(SlotType.FOUR_WHEELER)) {
	        revenue.setTotalRevenue4W(revenue.getTotalRevenue4W() + additionalPrice);
	    } else {
	        revenue.setTotalRevenue2W(revenue.getTotalRevenue2W() + additionalPrice);
	    }

	    // Assuming vendor gets 80% and admin gets 20%
	    double vendorEarning = additionalPrice * 0.8;
	    double adminEarning = additionalPrice * 0.2;
	    revenue.setVendorEarning(revenue.getVendorEarning() + vendorEarning);
	    revenue.setAdminEarning(revenue.getAdminEarning() + adminEarning);

	    revenueDao.save(revenue);

	    return new ApiResponse("Booking extended successfully! New end time: " + dto.getEndTime());
	}

	

}
