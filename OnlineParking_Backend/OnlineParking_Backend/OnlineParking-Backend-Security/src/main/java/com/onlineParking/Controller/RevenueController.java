package com.onlineParking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.onlineParking.DTO.RevenueRespDto;
import com.onlineParking.Services.RevenueService;

@RestController
@RequestMapping("/revenue")
@CrossOrigin(origins = "http://localhost:3000")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("/location/{locationId}")
    public ResponseEntity<?> getRevenueByLocation(@PathVariable Long locationId) {
        try {
            RevenueRespDto revenue = revenueService.getAllRevenueByLocation(locationId);
            return ResponseEntity.ok(revenue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/location/{locationId}/vehicleType")
    public ResponseEntity<?> getRevenueByLocationAndVehicleType(@PathVariable Long locationId,
                                                                 @RequestParam String vehicleType) {
        try {
            Double revenue = revenueService.findByLocationAndVehicleType(locationId, vehicleType);
            return ResponseEntity.ok(revenue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/location/{locationId}/vendor/{vendorId}")
    public ResponseEntity<?> getRevenueByLocationAndVendor(@PathVariable Long locationId, @PathVariable Long vendorId) {
        try {
            Double revenue = revenueService.findByLocationIdAndVendorId(locationId, vendorId);
            return ResponseEntity.ok(revenue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
