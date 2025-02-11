package com.packersandmovers.dto;

import java.math.BigDecimal;

import com.packersandmovers.pojos.ShiftingType;
import com.packersandmovers.pojos.Vendor;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VendorWeightPricingDTO 
{

	
    private Long vendor;

    private Long shiftingType;

    private Double minWeight;

    private Double maxWeight;

    private Double  pricePerKm;
	
}
