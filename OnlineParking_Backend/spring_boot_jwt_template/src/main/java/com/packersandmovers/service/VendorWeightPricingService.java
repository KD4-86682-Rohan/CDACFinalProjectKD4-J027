package com.packersandmovers.service;

import java.util.List;
import java.util.Optional;

import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.VendorWeightPricingDTO;
import com.packersandmovers.pojos.VendorWeightPricing;

public interface VendorWeightPricingService {

	ApiResponse savePricing(VendorWeightPricingDTO pricing);

	List<VendorWeightPricing> getAllPricing();

	Optional<VendorWeightPricing> getPricingById(Long id);

	//List<VendorWeightPricing> getPricingByVendorId(Long vendorId);

	void deletePricing(Long id);

}
