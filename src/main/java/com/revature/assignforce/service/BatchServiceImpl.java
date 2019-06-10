package com.revature.assignforce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.commands.FindCurriculumCommand;
import com.revature.assignforce.commands.FindLocationCommand;
import com.revature.assignforce.commands.FindSkillsCommand;
import com.revature.assignforce.commands.FindTrainerCommand;
import com.revature.assignforce.repos.BatchRepo;
import com.revature.assignforce.repos.SkillIdRepo;

@Transactional
@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepo batchRepo;

	@Autowired
	private BatchService batchService;
	
	@Autowired
	private SkillIdRepo skillIdRepo;
	
	@Autowired
	private FindTrainerCommand findTrainerCommand;
	
	@Autowired
	private FindLocationCommand findLocationCommand;
	
	@Autowired
	private FindCurriculumCommand findCurriculumCommand;
	
	@Autowired
	private FindSkillsCommand findSkillsCommand;

	@Override
	public List<Batch> getAll() {
		return batchRepo.findAll();
	}

	@Override
	public Optional<Batch> findById(int id) {
		return batchRepo.findById(id);
	}

	@Override
	public Batch update(Batch b) {
		for(SkillIdHolder s : b.getSkills()) {
			skillIdRepo.save(s);
		}
		return batchRepo.save(b);
	}
	
	/**
	 * Saves batch to the repository. The method first checks if there are skills
	 * present, if there is not, make a new HashSet. The references are then validated
	 * and finally it is saved.
	 * @param b - Batch to be saved
	 * @return batch created
	 */
	@Override
	public Batch create(Batch b) {
		Set<SkillIdHolder> skills = b.getSkills();
		if (skills == null) {
			skills = new HashSet<>();
			b.setSkills(skills);
		}
		
		// b = validateReferences(b);
		
		for(SkillIdHolder s : skills) {
			skillIdRepo.save(s);
		}
		
		return batchRepo.save(b);
	}

	@Override
	public void delete(int id) {
		Optional<Batch> batch = batchRepo.findById(id);
		if (!batch.isPresent()) {
			return;
		}
		batch.get().setSkills(new HashSet<SkillIdHolder>());
		batchRepo.save(batch.get());
		batchRepo.deleteById(id);
	}
	@Override
	public List<Batch> getAllByCurriculum(int curriculumId){
		return batchRepo.findByCurriculum(curriculumId);
	}
	@Override
	public List<Batch> getAllByTrainer(int trainerId){
		return batchRepo.findByTrainer(trainerId);
	}
	@Override
	public List<Batch> getAllByLocation(int locationId){
		return batchRepo.findByLocation(locationId);
	}
	
	/**
	 * Checks for referential integrity. The method will first call FindTrainer
	 * Command and check if the trainer exists, then move on to Location and Curriculum
	 * and finally, filters out all the skills that does not exist
	 * @param b Batch to be checked
	 * @return batch after all, if any, changes are made
	 */
	private Batch validateReferences(Batch b) {
		b = findTrainerCommand.findTrainer(b);
		b = findLocationCommand.findLocation(b);
		b = findCurriculumCommand.findCurriculum(b);
		b.setSkills(b.getSkills().stream().filter((skillIdHolder) -> findSkillsCommand.findSkill(skillIdHolder)).collect(Collectors.toSet()));
		return b;
	}

}
