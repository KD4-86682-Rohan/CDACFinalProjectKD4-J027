package com.onlineParking.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.PaymentRespDto;
import com.onlineParking.DTO.PaymentsReqDto;
import com.onlineParking.Dao.BookingDao;
import com.onlineParking.Dao.PaymentDao;
import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Pojos.PaymentStatus;
import com.onlineParking.Pojos.Payments;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	BookingDao bookingDao;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ApiResponse addPayment(Long bId, PaymentsReqDto dto) {
		    Bookings booking = bookingDao.findById(bId).orElseThrow(() -> new RuntimeException("Booking not found"));
		    Payments payments = modelMapper.map(dto, Payments.class);
		    payments.setBooking(booking);
		    paymentDao.save(payments);
	        return new ApiResponse("Payment added successfully");
	}

	@Override
	public List<PaymentRespDto> findByBookingId(Long bId) {
		Bookings booking = bookingDao.findById(bId).orElseThrow(() -> new RuntimeException("Booking not found"));
	    
		List<PaymentRespDto> payments = paymentDao.findByBooking(booking).stream()
                .map(payment -> modelMapper.map(payment, PaymentRespDto.class))
                .collect(Collectors.toList());
				
	    return payments;
	}

	@Override
	public List<PaymentRespDto> findByPaymentStatus(PaymentStatus status) {
		
		List<PaymentRespDto> payments = paymentDao.findByPaymentStatus(status).stream()
                .map(payment -> modelMapper.map(payment, PaymentRespDto.class))
                .collect(Collectors.toList());
		return payments;
	}
    
}
