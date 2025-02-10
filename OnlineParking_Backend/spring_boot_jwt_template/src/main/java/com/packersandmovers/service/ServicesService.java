package com.packersandmovers.service;

import java.util.List;

import com.packersandmovers.dto.ApiResponse;
import com.packersandmovers.dto.ServiceReqDTO;

public interface ServicesService {

	ApiResponse addService(ServiceReqDTO serviceReqDTO);

	List<ServiceReqDTO> getAllServices();

}
