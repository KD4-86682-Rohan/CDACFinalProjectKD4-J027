package com.packersandmovers.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packersandmovers.dao.*;
import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.ServiceRequestDTO;
import com.packersandmovers.pojos.PaymentStatus;
import com.packersandmovers.pojos.RequestStatus;
import com.packersandmovers.pojos.ServiceRequest;
import com.packersandmovers.pojos.Services;
import com.packersandmovers.pojos.ShiftingType;
import com.packersandmovers.pojos.User;
import com.packersandmovers.pojos.Vendor;
import com.packersandmovers.pojos.VendorWeightPricing;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceRequestServiceImpl implements ServiceRequestService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
     private VendorDao vendorDao;
	
	@Autowired
	private ShiftingTypeDao shiftingTypeDao;
	
	@Autowired
	private CityDistanceService cityDistanceService;
	
	@Autowired
	private VendorWeightPricingDao vendorWeightPricingDao;
	
	@Autowired
	private ServiceRequestDao serviceRequestDao;
	
	@Autowired
	private ServicesDao servicesDao;
	
	@Autowired
	private VendorServiceDao vendorServiceDao ;
	
	
	
	
	@Override
	public ApiResponse createServiceRequest(ServiceRequestDTO requestDTO) 
	{
		User user=userDao.findById(requestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found with ID: " + requestDTO.getUserId()));
		Vendor vendor=vendorDao.findById(requestDTO.getVendorId()).orElseThrow(() -> new RuntimeException("Vendor not found with ID: " + requestDTO.getVendorId()));
		ShiftingType shiftingType=shiftingTypeDao.findById(requestDTO.getShiftingId()).orElseThrow(() -> new RuntimeException(" shiftingType not found with ID: " + requestDTO.getShiftingId()));
		
		  Long distance = cityDistanceService.getDistance(requestDTO.getPickupLocation(), requestDTO.getDropoffLocation());
		    if (distance == null) {
		        throw new RuntimeException("Distance not found for the given locations: " +
		                requestDTO.getPickupLocation() + " to " + requestDTO.getDropoffLocation());
		    }
		    System.out.println(distance);
		   VendorWeightPricing weightPricing = vendorWeightPricingDao.findCostByVendorAndWeight(requestDTO.getVendorId(), requestDTO.getShipmentWeight()).orElseThrow(() -> new RuntimeException("No pricing found for weight range: " + requestDTO.getShipmentWeight()));
		   Double costPerKm=weightPricing.getPricePerKm();
		   System.out.println(costPerKm);
		   BigDecimal costPerKmBigDecimal = BigDecimal.valueOf(costPerKm);  
		   BigDecimal ditanceDecimal = BigDecimal.valueOf(distance);  
		   BigDecimal totalPrice = ditanceDecimal.multiply(costPerKmBigDecimal);
		   
		   Services services=servicesDao.findByServiceName(requestDTO.getAdditionalRequirements()).orElseThrow(()->new RuntimeException("No services found"));
		   Long addprice=vendorServiceDao.findPriceByServiceIdAndVendorId(services.getId(), requestDTO.getVendorId());
           System.out.println(addprice);
           System.out.println(totalPrice);
           totalPrice=totalPrice.add(BigDecimal.valueOf(addprice));
           
		   System.out.println(totalPrice);
		   ServiceRequest serviceRequest = new ServiceRequest();
		    serviceRequest.setUser(user);
		    serviceRequest.setVendor(vendor);
		    serviceRequest.setShiftingType(shiftingType);
		    serviceRequest.setPickupLocation(requestDTO.getPickupLocation());
		    serviceRequest.setDropoffLocation(requestDTO.getDropoffLocation());
		    serviceRequest.setPreferredDate(new java.sql.Date(requestDTO.getPreferredDate().getTime()));
		    serviceRequest.setAdditionalRequirements(requestDTO.getAdditionalRequirements());
		    serviceRequest.setRequestStatus(RequestStatus.PENDING);
		    serviceRequest.setPaymentStatus(PaymentStatus.PENDING);
		    serviceRequest.setShipmentWeight(requestDTO.getShipmentWeight());
		    serviceRequest.setTotalPrice(totalPrice);
		    serviceRequest.setIsDeleted(false);

		    serviceRequestDao.save(serviceRequest);
		
		
		
		
		return new ApiResponse("The total price of the service will be :"+totalPrice);
		
	}

}
