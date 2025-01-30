//package com.onlineParking.Pojos;
//
//import java.time.LocalDateTime;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name="Payments")
//@Getter
//@Setter
//@NoArgsConstructor
//@ToString(callSuper = true)
//public class Payments extends BaseEntity {
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "booking_id", nullable = false) // Foreign Key to Booking
//    private Bookings booking;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "payment_method", nullable = false)
//    private PaymentMethod paymentMethod;
//
//    @Column(nullable = false)
//    private Double amount;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "payment_status", nullable = false)
//    private PaymentStatus paymentStatus;
//
//    @Column(name = "transaction_id", unique = true)
//    private String transactionId;
//
//    @Column(name = "created_at", nullable = false)
//    private LocalDateTime createdAt;
//}
