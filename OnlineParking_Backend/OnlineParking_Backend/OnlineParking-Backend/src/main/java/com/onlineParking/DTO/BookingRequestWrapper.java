package com.onlineParking.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestWrapper {
    private BookingReqDto bookingReqDto;
    private VendorBookingDto vendorBookingDto;
}
