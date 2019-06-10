package com.revature.assignforce.repos;

import com.revature.assignforce.beans.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer>{
}
