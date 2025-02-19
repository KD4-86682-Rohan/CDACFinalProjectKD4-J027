package com.onlineParking.Services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.onlineParking.CustomExceptions.ApiException;
import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.BookingReqDto;
import com.onlineParking.DTO.BookingRespDto;
import com.onlineParking.DTO.VendorBookingDto;
import com.onlineParking.Dao.BookingDao;
import com.onlineParking.Dao.ParkingSlotDao;
import com.onlineParking.Dao.RevenueDao;
import com.onlineParking.Dao.SlotAvailabilityDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.BookingStatus;
import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.Revenue;
import com.onlineParking.Pojos.Role;
import com.onlineParking.Pojos.SlotAvailability;
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
	private SlotAvailabilityDao availabilityDao;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public List<BookingRespDto> getAllBookingsByVendor(Long vendorId) {
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
	
	public boolean isSlotAvailableForTime(Long slotId, LocalDate date, LocalTime startTime, LocalTime endTime) {
	    ParkingSlots slot = parkingSlotDao.findById(slotId)
	        .orElseThrow(() -> new ApiException("Invalid Slot ID"));

	    List<SlotAvailability> availabilities = availabilityDao.findBySlotAndDate(slot, date);
	    
	    return availabilities.stream()
	        .anyMatch(sa -> !sa.getIsBooked() && sa.getStartTime().equals(startTime) && sa.getEndTime().equals(endTime));
	}
	
	@Override
	public ApiResponse addBooking(Long userId, VendorBookingDto vendorIds, BookingReqDto dto) {
	    User user = userDao.findById(userId).orElseThrow(() -> new ApiException("Invalid User ID"));
	    User vendor = userDao.findById(vendorIds.getVendorId()).orElseThrow(() -> new ApiException("Invalid Vendor ID"));
	    ParkingSlots slot = parkingSlotDao.findById(vendorIds.getSlotId()).orElseThrow(() -> new ApiException("Invalid Slot"));

	    LocalDate bookingDate = dto.getStartTime().toLocalDate();
	    LocalTime startTime = dto.getStartTime().toLocalTime();
	    LocalTime endTime = dto.getEndTime().toLocalTime();

	    // ✅ **Check if Slot Availability Record Exists**
	    SlotAvailability availability = availabilityDao.findBySlotAndDateAndStartTime(slot, bookingDate, startTime)
	        .orElse(null);

	    // ✅ **If No Record Exists, Create a New One**
	    if (availability == null) {
	        availability = new SlotAvailability();
	        availability.setSlot(slot);
	        availability.setDate(bookingDate);
	        availability.setStartTime(startTime);
	        availability.setEndTime(endTime);
	        availability.setIsBooked(false); // Initially available
	    }

	    // ✅ **Check if Slot is Already Booked**
	    if (availability.getIsBooked()) {
	        throw new ApiException("Slot is already booked for the selected time.");
	    }

	    // ✅ **Calculate Price**
	    SlotType slotType = slot.getSlotType();
	    double pricePerHour = slotType.equals(SlotType.FOUR_WHEELER) ? slot.getPricePerHour4W() : slot.getPricePerHour2W();
	    Duration duration = Duration.between(dto.getStartTime(), dto.getEndTime());
	    double totalPrice = Math.abs(duration.toHours() * pricePerHour);

	    // ✅ **Save Booking**
	    Bookings booking = modelMapper.map(dto, Bookings.class);
	    booking.setUser(user);
	    booking.setVendor(vendor);
	    booking.setSlot(slot);
	    booking.setTotalPrice(totalPrice);
	    Bookings savedBooking = bookingDao.save(booking);

	    // ✅ **Mark Slot as Booked & Save Availability**
	    availability.setIsBooked(true);
	    availabilityDao.save(availability);

	    // ✅ **Update Parking Location Revenue**
	    ParkingLocation location = slot.getLocation();
	    Revenue revenue = revenueDao.findByLocation(location)
	        .stream()
	        .findFirst()
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

	    return new ApiResponse("Booking confirmed! Total Price: $" + totalPrice);
	}	
	

	@Override
	public ApiResponse cancelBooking(Long userId, Long bookingId) {
	    User user = userDao.findById(userId).orElseThrow(() -> new ApiException("Invalid User ID"));
	    if (!user.getRole().equals(Role.User)) {
	        return new ApiResponse("Only users can cancel bookings.");
	    }

	    Bookings booking = bookingDao.findById(bookingId)
	            .orElseThrow(() -> new ApiException("Invalid Booking ID"));

	    ParkingSlots slot = booking.getSlot();
	    ParkingLocation parkingLocation = slot.getLocation();

	    // ✅ **Update Slot Availability**
	    SlotAvailability availability = availabilityDao
	        .findBySlotAndDateAndStartTime(slot, booking.getStartTime().toLocalDate(), booking.getStartTime().toLocalTime())
	        .orElseThrow(() -> new ApiException("Slot availability not found"));

	    availability.setIsBooked(false);
	    availabilityDao.save(availability);

	    // ✅ **Refund Logic (Optional)**
	    Revenue revenue = revenueDao.findByLocation(parkingLocation)
	                                .stream()
	                                .findFirst()
	                                .orElseThrow(() -> new ApiException("Revenue entry not found"));

	    double refundAmount = booking.getTotalPrice();

	    revenue.setTotalRevenue(Math.max(0, revenue.getTotalRevenue() - refundAmount));

	    if (slot.getSlotType().equals(SlotType.FOUR_WHEELER)) {
	        revenue.setTotalRevenue4W(Math.max(0, revenue.getTotalRevenue4W() - refundAmount));
	    } else {
	        revenue.setTotalRevenue2W(Math.max(0, revenue.getTotalRevenue2W() - refundAmount));
	    }

	    // Revert earnings
	    double vendorEarning = refundAmount * 0.8;
	    double adminEarning = refundAmount * 0.2;
	    
	    revenue.setVendorEarning(Math.max(0, revenue.getVendorEarning() - vendorEarning));
	    revenue.setAdminEarning(Math.max(0, revenue.getAdminEarning() - adminEarning));

	    revenueDao.save(revenue);

	    // ✅ **Mark Booking as Canceled**
	    booking.setBookingStatus(false);
	    booking.setStatus(BookingStatus.CANCELLED);
	    bookingDao.save(booking);

	    return new ApiResponse("Booking canceled. Refund Amount: $" + refundAmount);
	}



	@Override
	public ApiResponse extendBooking(Long bookingId, BookingReqDto dto) {
	    Bookings booking = bookingDao.findById(bookingId)
	            .orElseThrow(() -> new ApiException("Invalid Booking ID"));

	    ParkingSlots slot = booking.getSlot();
	    ParkingLocation parkingLocation = slot.getLocation();

	    // ✅ **Check if New Slot is Available**
	    if (!isSlotAvailableForTime(slot.getId(), dto.getStartTime().toLocalDate(), dto.getStartTime().toLocalTime(), dto.getEndTime().toLocalTime())) {
	        throw new ApiException("New time slot is not available.");
	    }

	    // ✅ **Recalculate Price**
	    SlotType slotType = slot.getSlotType();
	    double pricePerHour = slotType.equals(SlotType.FOUR_WHEELER) ? slot.getPricePerHour4W() : slot.getPricePerHour2W();
	    Duration duration = Duration.between(dto.getStartTime(), dto.getEndTime());
	    double newTotalPrice = Math.abs(duration.toHours() * pricePerHour);

	    // ✅ **Update Revenue**
	    Revenue revenue = revenueDao.findByLocation(parkingLocation)
				.stream().findFirst().orElseGet(() -> {
					Revenue newRevenue = new Revenue();
					newRevenue.setLocation(parkingLocation);
					newRevenue.setUser(booking.getVendor());
					newRevenue.setTotalRevenue(0.0);
					newRevenue.setTotalRevenue2W(0.0);
					newRevenue.setTotalRevenue4W(0.0);
					newRevenue.setVendorEarning(0.0);
					newRevenue.setAdminEarning(0.0);
					return revenueDao.save(newRevenue);
				});
	    double oldPrice = booking.getTotalPrice();

		revenue.setTotalRevenue(revenue.getTotalRevenue()-oldPrice + newTotalPrice);

		if (slot.getSlotType().equals(SlotType.FOUR_WHEELER)) {
			revenue.setTotalRevenue4W(revenue.getTotalRevenue4W()-oldPrice + newTotalPrice);
		} else {
			revenue.setTotalRevenue2W(revenue.getTotalRevenue2W()-oldPrice + newTotalPrice);
		}

// Assuming vendor gets 80% and admin gets 20%
		double vendorEarning = newTotalPrice * 0.8;
		double adminEarning = newTotalPrice * 0.2;

		revenue.setVendorEarning(revenue.getVendorEarning()-oldPrice + vendorEarning);
		revenue.setAdminEarning(revenue.getAdminEarning()-oldPrice + adminEarning);

		revenueDao.save(revenue);

	    // ✅ **Update Booking Details**
	    booking.setStartTime(dto.getStartTime());
	    booking.setEndTime(dto.getEndTime());
	    booking.setTotalPrice(newTotalPrice);
	    bookingDao.save(booking);

	    return new ApiResponse("Booking updated successfully! New Total Price: $" + newTotalPrice);
	}


	

}
