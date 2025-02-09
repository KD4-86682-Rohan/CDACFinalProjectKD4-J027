package com.packersandmovers.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.packersandmovers.pojos.VendorWeightPricing;


import java.util.Optional;


public interface VendorWeightPricingDao extends JpaRepository<VendorWeightPricing, Long> {

	@Query("SELECT vwp FROM VendorWeightPricing vwp WHERE vwp.vendor.id = :vendorId AND :weight BETWEEN vwp.minWeight AND vwp.maxWeight")
	Optional<VendorWeightPricing> findCostByVendorAndWeight(@Param("vendorId") Long vendorId, @Param("weight") Double weight);
 
}

