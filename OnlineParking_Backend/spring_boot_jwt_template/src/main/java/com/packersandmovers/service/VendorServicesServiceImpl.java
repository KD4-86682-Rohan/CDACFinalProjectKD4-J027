package com.packersandmovers.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.packersandmovers.dao.ServicesDao;
import com.packersandmovers.dao.VendorDao;
import com.packersandmovers.dao.VendorServiceDao;
import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.VendorsServiceDTO;
import com.packersandmovers.pojos.Services;
import com.packersandmovers.pojos.UserRole;
import com.packersandmovers.pojos.Vendor;
import com.packersandmovers.pojos.VendorServices;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor

@Service
@Transactional
public class VendorServicesServiceImpl implements VendorServicesService 
{
	@Autowired
	private VendorServiceDao vendorServiceDao;
	
	@Autowired
	private VendorDao vendorDao;
	
	@Autowired
	private ServicesDao serviceDao;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public ApiResponse addVendorService(VendorsServiceDTO vendorServiceDto) 
	{
		Vendor vendor = vendorDao.findById(vendorServiceDto.getVendorid()).orElseThrow(() -> new RuntimeException("Vendor not found with ID: " +vendorServiceDto.getVendorid()));
		Services service = serviceDao.findById(vendorServiceDto.getServicesid()).orElseThrow(() -> new RuntimeException("Vendor not found with ID: " +vendorServiceDto.getServicesid()));
		
		
		
		VendorServices vendorServiceEnity = new VendorServices();
		vendorServiceEnity.setServices(service);
		vendorServiceEnity.setVendor(vendor);
		vendorServiceEnity.setPrice(vendorServiceDto.getPrice());
		
		vendorServiceDao.save(vendorServiceEnity);
		
		return new ApiResponse("Added vendorService successfully !!!");
		
		
		
			
	}

}
