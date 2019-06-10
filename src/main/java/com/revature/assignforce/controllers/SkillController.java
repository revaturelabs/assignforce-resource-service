package com.revature.assignforce.controllers;

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.containers.SkillsArray;
import com.revature.assignforce.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class SkillController {

	Logger logger = LoggerFactory.getLogger(SkillController.class);

	@Autowired
	private SkillService skillServ;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Skill> getAll() {
		
		return skillServ.getAll();
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> getById(@PathVariable("id") int id) {
		Optional<Skill> skill = skillServ.getSkillById(id);
		if (!skill.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(skill.get(), HttpStatus.OK);
	}

	@PostMapping(value = "by-array", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Skill>> getByArray(@RequestBody @Valid SkillsArray arr) {
		List<Skill> skills = new ArrayList<>();
		for (Skill skl : arr.getSkills()) {
			Optional<Skill> temp = skillServ.getSkillById(skl.getId());
			if (temp.isPresent()) {
				skills.add(temp.get());
			}
		}
		return new ResponseEntity<>(skills, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> saveNewSkill(@RequestBody @Validated(Skill.New.class) Skill skill) {
		Skill newSkill = skillServ.createSkill(skill);


		if (newSkill != null) {
			return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Skill> deleteSkill(@PathVariable("id") int id) {
		skillServ.deleteSkill(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> updateSkill(@RequestBody @Validated(Skill.Existing.class) Skill skill) {
		logger.info("Updating skill ", skill.getName());

		//we are going to assume that we are only deactivating
		//the skill for now
		Skill newSkill = skillServ.updateSkill(skill);

		if (newSkill != null) {
			return new ResponseEntity<>(newSkill, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
