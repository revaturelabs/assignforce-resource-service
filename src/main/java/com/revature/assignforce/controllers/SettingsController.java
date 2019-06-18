package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SettingsController {

	@Autowired
	SettingsService settingService;

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Settings> getById(@PathVariable int id) {
		Optional<Settings> s = settingService.findById(id);
		if (!s.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(s.get(), HttpStatus.OK);
	}

	// update
	@PutMapping
	public ResponseEntity<Settings> update(@RequestBody Settings s) {
		s = settingService.update(s);
		if (s == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

}
