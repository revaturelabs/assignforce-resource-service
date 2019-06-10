package com.revature.assignforce.service;

import com.revature.assignforce.beans.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
	public List<Skill> getAll();
	public Optional<Skill> getSkillById(int id);
	public Skill createSkill(Skill skill);
	public Skill updateSkill(Skill skill);
	public void deleteSkill(int id);
}
