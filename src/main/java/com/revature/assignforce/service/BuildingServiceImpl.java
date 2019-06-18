package com.revature.assignforce.service;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.repos.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

	
	@Autowired
	private BuildingRepo buildingRepo;

	@Override
	public List<Building> getAll() {
		return buildingRepo.findAll();
	}

	@Override
	public Optional<Building> findById(int id) {
		return buildingRepo.findById(id);
	}

	@Override
	public Building update(Building t) {
		return buildingRepo.save(t);
	}

	@Override
	public Building create(Building t) {
		return buildingRepo.save(t);
	}

	@Override
	public void delete(int id) {
		buildingRepo.deleteById(id);
	}
}
