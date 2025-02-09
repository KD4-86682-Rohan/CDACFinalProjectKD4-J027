package com.packersandmovers.service;

import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.VendorsServiceDTO;
import com.packersandmovers.pojos.VendorServices;

public interface VendorServicesService 
{
	ApiResponse addVendorService(VendorsServiceDTO vendorServiceDto);
	

}
