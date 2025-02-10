package com.onlineParking.DTO;

import com.onlineParking.Pojos.PaymentMethod;
import com.onlineParking.Pojos.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentsReqDto {

	private PaymentMethod paymentMethod;
    private Double amount;
    private PaymentStatus paymentStatus;
    private String transactionId;
}
