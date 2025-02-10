package com.packersandmovers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packersandmovers.dto.VendorsServiceDTO;
import com.packersandmovers.service.VendorServicesService;



@RestController
@RequestMapping("/VendorServices")

public class VendorServicesController 
{
	@Autowired
	private VendorServicesService vendorServicesService;
	
	public VendorServicesController()
	{
		System.out.println("Inside VendorServiceController");
		
	}
	
	@PostMapping
	public ResponseEntity<?> addVServices(@RequestBody VendorsServiceDTO vendorDto)
	{
		System.out.println("In  :"+vendorDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(vendorServicesService.addVendorService(vendorDto));
	}

}
