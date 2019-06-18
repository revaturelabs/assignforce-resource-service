package com.revature.assignforce.service;

import com.revature.assignforce.beans.Location;
import com.revature.assignforce.repos.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepo locationRepo;

	@Override
	public List<Location> getAll() {
		return locationRepo.findAll();
	}

	@Override
	public Optional<Location> findById(int id) {
		return locationRepo.findById(id);
	}

	@Override
	public Location update(Location t) {
		return locationRepo.save(t);
	}

	@Override
	public Location create(Location t) {
		return locationRepo.save(t);
	}

	@Override
	public void delete(int id) {
		locationRepo.deleteById(id);
	}
}
