package com.packersandmovers.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.packersandmovers.pojos.VendorServices;


public interface VendorServiceDao extends JpaRepository<VendorServices, Long> 
{
	@Query("SELECT v.price FROM VendorServices v WHERE v.services.id = :serviceId AND v.vendor.id = :vendorId")
    Long findPriceByServiceIdAndVendorId(@Param("serviceId") Long serviceId, @Param("vendorId") Long vendorId);
}
