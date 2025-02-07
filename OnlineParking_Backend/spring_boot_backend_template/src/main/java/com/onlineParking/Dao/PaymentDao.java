package com.onlineParking.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlineParking.Pojos.Payments;
import com.onlineParking.Pojos.Bookings;
import com.onlineParking.Pojos.PaymentStatus;

public interface PaymentDao extends JpaRepository<Payments, Long> {
    List<Payments> findByBooking(Bookings booking);
    List<Payments> findByPaymentStatus(PaymentStatus status);
}