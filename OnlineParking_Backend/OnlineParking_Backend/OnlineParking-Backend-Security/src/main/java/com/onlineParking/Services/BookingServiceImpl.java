package com.onlineParking.Services;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
import com.onlineParking.Dao.BookingDao;
import com.onlineParking.Dao.NotificationDao;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.ParkingSlotDao;
import com.onlineParking.Dao.RevenueDao;
import com.onlineParking.Dao.SlotAvailabilityDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.BookingStatus;
import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Pojos.Notifications;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.ParkingSlots;
import com.onlineParking.Pojos.Revenue;
import com.onlineParking.Pojos.SlotAvailability;
import com.onlineParking.Pojos.SlotType;
import com.onlineParking.Pojos.User;


@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	private ParkingSlotDao parkingSlotDao;
	
	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private RevenueDao revenueDao;
	
	@Autowired
	private SlotAvailabilityService availabilityService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SlotAvailabilityDao availabilityDao;
	
	@Autowired
	private NotificationDao notificationDao;
	
	@Autowired
	private ParkingLocationDao parkingLocationDao;
	
	@Override
	public List<BookingRespDto> getAllBookingsByUserId(Long userId) {
		User user = userDao.findById(userId).orElseThrow(()-> new ApiException("Invalid User"));
	    List<Bookings> bookings = bookingDao.findByUser(user);

	    if (bookings.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No bookings found for this user.");
	    }

	    return bookings.stream()
	        .map(booking -> modelMapper.map(booking, BookingRespDto.class))
	        .collect(Collectors.toList());
	}
	
	@Override
	public List<BookingRespDto> getAllBookingsByVendorId(Long vendorId) {
		User vendor = userDao.findById(vendorId).orElseThrow(()-> new ApiException("Invalid Vendor"));
	    List<Bookings> bookings = bookingDao.findByVendor(vendor);

	    if (bookings.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No bookings found for this vendor.");
	    }

	    return bookings.stream()
	        .map(booking -> modelMapper.map(booking, BookingRespDto.class))
	        .collect(Collectors.toList());
	}
	
	@Override
	public List<BookingRespDto> getAllBookingsByVendorAndLocation(Long vendorId, Long locationId) {
		User vendor = userDao.findById(vendorId).orElseThrow(()-> new ApiException("Invalid Vendor"));
		ParkingLocation parkingLocation = parkingLocationDao.findById(locationId).orElseThrow(()-> new ApiException("Invalid Vendor"));
	    List<Bookings> bookings = bookingDao.findByVendorAndLocation(vendor, parkingLocation);

	    if (bookings.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No bookings found for this vendor at this location.");
	    }

	    return bookings.stream()
	        .map(booking -> modelMapper.map(booking, BookingRespDto.class))
	        .collect(Collectors.toList());
	}



	@Override
	public Bookings createBooking(Long userId, Long slotId, BookingReqDto dto) {
	    User user = userDao.findById(userId).orElseThrow(()-> new ApiException("invalid user"));
	    ParkingSlots slot = parkingSlotDao.findById(slotId).orElseThrow(() -> new RuntimeException("Slot not found"));
	    ParkingLocation location = slot.getLocation();
	    User vendor = location.getVendor();

	    LocalDateTime startTime = dto.getStartTime();
	    LocalDateTime endTime = dto.getEndTime();

	    // Check if slot is available
	    boolean available = availabilityService.isSlotAvailable(slotId, startTime.toLocalDate(),
	            startTime, endTime);
	    if (!available) {
	        throw new ApiException("Slot is not available");
	    }

	    // Calculate duration
	    long durationInMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
	    double durationInHours = Math.round((double) durationInMinutes / 60 * 100.0) / 100.0;

	    if (durationInHours <= 0) {
	        throw new IllegalArgumentException("End time must be after start time.");
	    }

	    // Calculate total price
	    double pricePerHour = (slot.getSlotType() == SlotType.TWO_WHEELER)
	            ? slot.getPricePerHour2W()
	            : slot.getPricePerHour4W();

	    double totalPrice = pricePerHour * durationInHours;
	    totalPrice = Math.round(totalPrice * 100.0) / 100.0;

	    // Create and save booking
	    Bookings booking = modelMapper.map(dto, Bookings.class);
	    booking.setUser(user);
	    booking.setVendor(vendor);
	    booking.setSlot(slot);
	    booking.setBookingTime(LocalDateTime.now());
	    booking.setStatus(BookingStatus.UPCOMING);
	    booking.setTotalPrice(totalPrice);

	    booking = bookingDao.save(booking);

	    // Adjust slot availability
	    availabilityService.adjustSlotAvailability(slotId, startTime.toLocalDate(), startTime,
	            endTime);

	    // Add Notification for Booking
        Notifications notification = new Notifications();
        notification.setUser(user);
        notification.setMessage("Your booking for slot " + slot.getSlotNumber() + " is confirmed.");
        notification.setIsRead(false);
        notificationDao.save(notification);
	    
	    // Update revenue
	    Revenue revenue = revenueDao.findByLocation(location);
	    if (revenue == null) {
	        revenue = new Revenue();
	        revenue.setLocation(location);
	        revenue.setTotalRevenue(totalPrice);
	        revenue.setVendorEarning(Math.round(totalPrice * 0.90 * 100.0) / 100.0);
	        revenue.setAdminEarning(Math.round(totalPrice * 0.10 * 100.0) / 100.0);
	    } else {
	        revenue.setTotalRevenue(Math.round((revenue.getTotalRevenue() + totalPrice) * 100.0) / 100.0);
	        revenue.setVendorEarning(Math.round((revenue.getVendorEarning() + (totalPrice * 0.90)) * 100.0) / 100.0);
	        revenue.setAdminEarning(Math.round((revenue.getAdminEarning() + (totalPrice * 0.10)) * 100.0) / 100.0);
	    }

	    revenueDao.save(revenue);

	    return booking;
	}
	
	@Override
	public ApiResponse cancelBooking(Long bookingId, Long userId) {
		User user = userDao.findById(userId).orElseThrow(()-> new ApiException("invalid user"));
	    Bookings booking = bookingDao.findById(bookingId)
	        .orElseThrow(() -> new ApiException("Booking not found"));
	    
	    if(booking.getUser()!=user) {
	    	return new ApiResponse("you can cancle only your booking");
	    }

	    List<SlotAvailability> adjacentSlots = availabilityDao.findBySlotAndDate(
	        booking.getSlot(), booking.getBookingTime().toLocalDate());

	    SlotAvailability newAvailability = new SlotAvailability();
	    newAvailability.setSlot(booking.getSlot());
	    newAvailability.setDate(booking.getBookingTime().toLocalDate());
	    newAvailability.setStartTime(booking.getStartTime());
	    newAvailability.setEndTime(booking.getEndTime());
	    newAvailability.setIsBooked(false);

	    // Try merging with adjacent slots
	    for (SlotAvailability slot : adjacentSlots) {
	        if (!slot.getIsBooked()) {
	            if (slot.getEndTime().equals(newAvailability.getStartTime())) {
	                // Merge: Extend start time
	                newAvailability.setStartTime(slot.getStartTime());
	                availabilityDao.delete(slot);
	            } else if (slot.getStartTime().equals(newAvailability.getEndTime())) {
	                // Merge: Extend end time
	                newAvailability.setEndTime(slot.getEndTime());
	                availabilityDao.delete(slot);
	            }
	        }
	    }
	    availabilityDao.save(newAvailability);
	    
	 // Add Notification for Cancellation
        Notifications notification = new Notifications();
        notification.setUser(user);
        notification.setMessage("Your booking for slot " + booking.getSlot().getSlotNumber() + " has been canceled.");
        notification.setIsRead(false);
        notificationDao.save(notification);
	    
	    updateRevenueAfterCancellation(booking);

	    return new ApiResponse("Booking canceled, slot availability updated.");
	}
	
	private void updateRevenueAfterCancellation(Bookings booking) {
	    Revenue revenue = revenueDao.findByLocation(booking.getSlot().getLocation());

	    double refundAmount = booking.getTotalPrice();

	    revenue.setTotalRevenue(revenue.getTotalRevenue() - refundAmount);

	    double adminShare = refundAmount * 0.10; 
	    double vendorShare = refundAmount - adminShare;

	    revenue.setAdminEarning(revenue.getAdminEarning() - adminShare);
	    revenue.setVendorEarning(revenue.getVendorEarning() - vendorShare);

	    revenueDao.save(revenue);
	}


}
