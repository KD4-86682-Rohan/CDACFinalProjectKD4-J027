package com.onlineParking.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlineParking.DTO.ApiResponse;
import com.onlineParking.DTO.CityDto;
import com.onlineParking.DTO.ParkingLocationReqDto;
import com.onlineParking.DTO.ParkingLocationRespDto;
import com.onlineParking.Dao.ParkingLocationDao;
import com.onlineParking.Dao.UserDao;
import com.onlineParking.Pojos.ParkingLocation;
import com.onlineParking.Pojos.Role;
import com.onlineParking.Pojos.User;

@Service
@Transactional
public class ParkingLocationServiceImpl implements ParkingLocationService {

	@Autowired
	private ParkingLocationDao parkingLocationDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	// get all parking locations
	@Override
	public List<ParkingLocationRespDto> getAllParkingLocations() {

		return parkingLocationDao.findAll().stream().filter(location -> location.isStatus())
				.map(location -> modelMapper.map(location, ParkingLocationRespDto.class)).collect(Collectors.toList());
	}

	// add new parking location
	@Override
	public ApiResponse addNewParkingLocation(ParkingLocationReqDto dto, Long vendorId) {
		User user = userDao.findById(vendorId).orElseThrow(() -> new RuntimeException("Invalid Id"));
		if (user.getRole().equals(Role.Vendor)) {
			ParkingLocation location = modelMapper.map(dto, ParkingLocation.class);
			location.setVendor(user);
			ParkingLocation persistentLocation = parkingLocationDao.save(location);
			return new ApiResponse("new parking location added with id=" + persistentLocation.getId());
		}
		return new ApiResponse("location not added");

	}

	@Override
	public ApiResponse deleteParkingLocation(Long id, Long vId) {
		User user = userDao.findById(vId).orElseThrow(() -> new RuntimeException("Invalid Id"));
		if (user.getRole().equals(Role.Vendor)) {
			ParkingLocation parkingLocation = parkingLocationDao.findById(id)
					.orElseThrow(() -> new RuntimeException("Invalid Location Id"));
			parkingLocation.setStatus(false);
			parkingLocationDao.save(parkingLocation);
			return new ApiResponse("parking location deleated");
		}
		return new ApiResponse("location not deleted");
		
	}

	@Override
	public ApiResponse updateParkingLocation(ParkingLocationReqDto dto, Long id, Long vId) {
		User user = userDao.findById(vId).orElseThrow(() -> new RuntimeException("Invalid Id"));
		if (user.getRole().equals(Role.Vendor)) {
			ParkingLocation parkingLocation = parkingLocationDao.findById(id)
					.orElseThrow(() -> new RuntimeException("Invalid Location Id"));
			parkingLocation.setArea(dto.getArea());
			parkingLocation.setCity(dto.getCity());
			parkingLocationDao.save(parkingLocation);
			return new ApiResponse("parking location Updated");
		}
		return new ApiResponse("location not Updated");
		
	}

	  @Override
	    public List<CityDto> getAllDistinctCities() {
	        // Define a TypeMap for String to CityDto conversion
	        TypeMap<String, CityDto> typeMap = modelMapper.typeMap(String.class, CityDto.class);
	        typeMap.addMapping(cityName -> cityName, CityDto::setCity);

	        // Retrieve distinct city names and map them to CityDto
	        return parkingLocationDao.findDistinctCities()
	            .stream()
	            .map(cityName -> modelMapper.map(cityName, CityDto.class))
	            .collect(Collectors.toList());
	    }
	  
	  @Override
	  public List<ParkingLocationRespDto> searchParkingLocations(String city, String area) {
	      List<ParkingLocation> locations = parkingLocationDao.findAll()
	              .stream()
	              .filter(loc -> (city == null || loc.getCity().equalsIgnoreCase(city)) &&
	                             (area == null || loc.getArea().equalsIgnoreCase(area)))
	              .collect(Collectors.toList());

	      return locations.stream()
	              .map(loc -> modelMapper.map(loc, ParkingLocationRespDto.class))
	              .collect(Collectors.toList());
	  }


}
