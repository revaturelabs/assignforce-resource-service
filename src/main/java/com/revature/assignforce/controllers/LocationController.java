package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.Location;
import com.revature.assignforce.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {

	@Autowired
	LocationService locationService;

	// findAll
	@GetMapping
	public List<Location> getAll() {
		return locationService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Location> getById(@PathVariable("id") int id) {
		Optional<Location> a = locationService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	// create
	@PostMapping
	public ResponseEntity<Location> add(@RequestBody Location a) {
		a = locationService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// update
	@PutMapping
	public ResponseEntity<Location> update(@RequestBody Location a) {
		a = locationService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Location> delete(@PathVariable("id") int id) {
		locationService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
