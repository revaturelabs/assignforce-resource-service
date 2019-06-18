package com.revature.assignforce.repos;

import com.revature.assignforce.beans.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepo extends JpaRepository<Trainer, Integer>{

	Optional<Trainer> findByEmail(String email);

	List<Trainer> findByPreferredLocation(Integer preferredLocation);	
	
}
