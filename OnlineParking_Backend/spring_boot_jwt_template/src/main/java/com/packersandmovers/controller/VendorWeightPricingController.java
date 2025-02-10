package com.packersandmovers.controller;


import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.VendorWeightPricingDTO;
import com.packersandmovers.pojos.VendorWeightPricing;
import com.packersandmovers.service.VendorWeightPricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendor-weight-pricing")
public class VendorWeightPricingController {

    @Autowired
    private VendorWeightPricingService vendorWeightPricingService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPricing(@RequestBody VendorWeightPricingDTO pricing) {
    	System.out.println(pricing);
        return ResponseEntity.ok(vendorWeightPricingService.savePricing(pricing));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VendorWeightPricing>> getAllPricing() {
        return ResponseEntity.ok(vendorWeightPricingService.getAllPricing());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorWeightPricing> getPricingById(@PathVariable Long id) {
        Optional<VendorWeightPricing> pricing = vendorWeightPricingService.getPricingById(id);
        return pricing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePricing(@PathVariable Long id) {
        vendorWeightPricingService.deletePricing(id);
        return ResponseEntity.ok("Pricing deleted successfully");
    }
}

