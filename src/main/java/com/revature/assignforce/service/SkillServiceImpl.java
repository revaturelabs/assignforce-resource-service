package com.revature.assignforce.service;

import com.revature.assignforce.beans.SkillsNotifier;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.repos.SkillRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {
	private static String name = "Skill";
	private static final Logger LOG = LoggerFactory.getLogger(name);

	@Autowired
	SkillRepo skillRepo;

	SkillsSNSNotificationSender notificationSender;

	@Autowired
	public void setNotificationSender(SkillsSNSNotificationSender notificationSender) {
		this.notificationSender = notificationSender;
	}

	@Override
	public Optional<Skill> getSkillById(int id) {
		return skillRepo.findById(id);
	}

	@Override
	public List<Skill> getAll() {
		
		return skillRepo.findAll();
	}

	@Override // Create Check for Duplicate skill name. If duplicate, ignore.
	public Skill createSkill(Skill skill) {
		Skill s = skillRepo.save(skill);
		notificationSender.sendAddNotification(new SkillsNotifier(s.getId()));
		return s;

	}

	@Override
	public Skill updateSkill(Skill skill) {
		LOG.info("Pushing message for deleting skill {}", skill.getId());
		notificationSender.sendDeleteNotification(new SkillsNotifier(skill.getId()));
		return skillRepo.save(skill);
	}

	@Override
	public void deleteSkill(int id) {
		skillRepo.deleteById(id);
	}
	
}
