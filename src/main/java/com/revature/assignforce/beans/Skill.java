package com.revature.assignforce.beans;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Component
@Entity
@Table(name="SKILL")
public class Skill {

	// Weird Annotations

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="skill")
	@SequenceGenerator(name="skill", sequenceName="skill_seq", allocationSize=1)
	@Column(name="SKILL_ID")
	@NotNull(message = "Skill must have id.", groups = {New.class, Existing.class})
	@Range(min = 0, max = 0, message = "New Skill must have id of 0", groups = {New.class})
	@Positive(message = "Existing Skill must have a positive id number", groups = {Existing.class})
	private Integer id;
	
	@Column(name = "SKILLNAME")
	@NotEmpty(message = "Skill must not be empty.")
	@NotNull(message = "Skill must have a name.", groups = {New.class, Existing.class})
	@Size(min = 1, max = 128, message = "Skill name must be between 1 and 128 characters", groups = {New.class, Existing.class})
	private String name;
	
	@Column(name="IS_ACTIVE")
	@NotNull(message = "Skill must define whether it is active.", groups = {New.class, Existing.class})
	private Boolean isActive;

	//Validation groups
	public interface Existing {}
	public interface New {}
	
	//constructors
	public Skill() {
		super();
	}

	public Skill(int id, String name, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}	
}
