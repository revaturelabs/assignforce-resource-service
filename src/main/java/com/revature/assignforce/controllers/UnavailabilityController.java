package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.LocationUnavailability;
import com.revature.assignforce.service.RoomService;
import com.revature.assignforce.service.UnavailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unavailabilities")
public class UnavailabilityController {
	
	@Autowired
	UnavailabilityService unavailabilityService;
	@Autowired
	RoomService roomService;

	// findAll
	@GetMapping
	public List<LocationUnavailability> getAll() {
		
		
		
		return unavailabilityService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<LocationUnavailability> getById(@PathVariable("id") int id) {
		
		
		Optional<LocationUnavailability> a = unavailabilityService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	/** create, also need to send room id through the url because we cannot set the room field in 
	LocationUnavailability (because the room field is updatable=false and insertable=false), the only possible way is to
	 set the roomObject to finding the room by id and then setting roomObject to the object returned (which will also change the room 
	field in LocationUnavailability to the primary key of the room)
	*/
	@PostMapping
	public ResponseEntity<LocationUnavailability> add(@RequestBody LocationUnavailability a) {
	
		//find by room to add to LocationUnavailability object
		
		a = unavailabilityService.addUnavailability(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	@PutMapping(value = "{uaId}")
	public ResponseEntity<LocationUnavailability> update(@PathVariable("uaId") int uaId, @RequestBody LocationUnavailability a) {
		a = unavailabilityService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<LocationUnavailability> delete(@PathVariable("id") int id) {
		unavailabilityService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
