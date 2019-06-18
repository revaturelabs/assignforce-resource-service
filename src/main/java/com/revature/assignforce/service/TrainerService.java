package com.revature.assignforce.service;

import com.revature.assignforce.beans.Trainer;

import java.util.List;
import java.util.Optional;

public interface TrainerService {

	List<Trainer> getAll();
	Optional<Trainer> findById(int id);
	Trainer update(Trainer t);
	Trainer create(Trainer t);
	void delete(int id);
	Optional<Trainer> findByEmail(String email);
	List<Trainer> findByPreferredLocation(Integer preferredLocation);
	
}
