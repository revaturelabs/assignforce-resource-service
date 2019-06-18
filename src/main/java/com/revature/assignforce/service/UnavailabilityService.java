package com.revature.assignforce.service;

import com.revature.assignforce.beans.LocationUnavailability;

import java.util.List;
import java.util.Optional;

public interface UnavailabilityService {
	

	List<LocationUnavailability> getAll();

	Optional<LocationUnavailability> findById(int id);

	LocationUnavailability update(LocationUnavailability t);

	LocationUnavailability create(LocationUnavailability t);

	void delete(int id);
	
	public LocationUnavailability addUnavailability(LocationUnavailability t);
	
	public LocationUnavailability updateUnavailability(LocationUnavailability t);


}
