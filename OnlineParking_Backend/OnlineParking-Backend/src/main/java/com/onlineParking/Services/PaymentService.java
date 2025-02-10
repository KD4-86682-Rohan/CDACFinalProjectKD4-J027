package com.onlineParking.Services;

import java.util.List;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.PaymentRespDto;
import com.onlineParking.DTO.PaymentsReqDto;
import com.onlineParking.Pojos.PaymentStatus;

public interface PaymentService {
	ApiResponse addPayment(Long bId, PaymentsReqDto dto);
	List<PaymentRespDto> findByBookingId(Long id);
	List<PaymentRespDto> findByPaymentStatus(PaymentStatus status);
}
