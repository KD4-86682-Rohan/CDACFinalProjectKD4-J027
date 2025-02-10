package com.packersandmovers.service;

import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.ServiceRequestDTO;

public interface ServiceRequestService {

	ApiResponse createServiceRequest(ServiceRequestDTO requestDTO);

}
