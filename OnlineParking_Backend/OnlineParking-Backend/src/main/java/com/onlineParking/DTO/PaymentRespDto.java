package com.onlineParking.DTO;

import com.onlineParking.Pojos.PaymentMethod;
import com.onlineParking.Pojos.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class PaymentRespDto {

	private PaymentMethod paymentMethod;
    private Double amount;
    private PaymentStatus paymentStatus;
    private String transactionId;
}
