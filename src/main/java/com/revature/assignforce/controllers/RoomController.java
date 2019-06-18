package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	RoomService roomService;

	// findAll
	@GetMapping
	public List<Room> getAll() {
		
		
		return roomService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Room> getById(@PathVariable("id") int id) {
		Optional<Room> a = roomService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	// create
	@PostMapping
	public ResponseEntity<Room> add(@RequestBody Room a) {
		a = roomService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// update
	@PutMapping("{id}")
	public ResponseEntity<Room> update(@RequestBody Room a) {
		a = roomService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Room> delete(@PathVariable("id") int id) {
		roomService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
