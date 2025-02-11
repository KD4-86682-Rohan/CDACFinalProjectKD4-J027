package com.packersandmovers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.ServiceRequestDTO;
import com.packersandmovers.service.ServiceRequestService;

@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController 
{
    @Autowired
    ServiceRequestService serviceRequestService;
	
	@PostMapping
    public ResponseEntity<ApiResponse> createServiceRequest(@RequestBody ServiceRequestDTO requestDTO) {
        ApiResponse createdRequest = serviceRequestService.createServiceRequest(requestDTO);
        return ResponseEntity.ok(createdRequest);
    }
}
