package com.packersandmovers.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packersandmovers.pojos.ServiceRequest;

public interface ServiceRequestDao extends JpaRepository<ServiceRequest, Long>   {

}
