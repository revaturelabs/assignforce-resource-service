package com.revature.assignforce.service;

import com.revature.assignforce.beans.Building;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
	
	List<Building> getAll();

	Optional<Building> findById(int id);

	Building update(Building t);

	Building create(Building t);

	void delete(int id);

}
