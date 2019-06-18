package com.revature.testing.Skill;

import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.beans.Skill.Existing;
import com.revature.assignforce.beans.Skill.New;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillTest {

	@Configuration
	static class SkillTestContextConfiguration {
		@Bean
		public Skill Skill() {
			return new Skill();
		}
	}

	@Test
	public void skillTest1() {
		Skill s1 = new Skill();
		assertNotNull(s1);
	}
	
	// This function will test if the getId() method corresponds with the id
	// that is given when created
	@Test
	public void skillTest2() {
		int testId = 4;
		String testName = "Swift";
		boolean testActive = true;
		Skill s1 = new Skill(testId, testName, testActive);
		assertTrue("Expected id " + testId + ". Got " + s1.getId(), s1.getId() == testId);
		assertTrue(s1.getName().equals("Swift"));
		assertTrue(s1.getIsActive());
	}
	
	// This function is to test if the setId() actually sets the new id and overrides
	// the previous id that is set
	@Test
	public void getSetIdTest() {
		Skill s1 = new Skill();
		s1.setId(11);
		assertTrue(s1.getId() == 11);
	}
	
	// This function is to test if the setName() actually sets the new skillName and overrides
	// the previous skillName that is set
	@Test
	public void getSetNameTest() {
		Skill s1 = new Skill();
		s1.setName("JavaScript");
		assertTrue(s1.getName().equals("JavaScript"));
	}
	
	// This function is to test if the setIsActive() actually method sets the skill to true 
	@Test
	public void getSetIsActiveTest() {
		Skill s1 = new Skill();
		s1.setIsActive(true);
		assertTrue(s1.getIsActive());
	}
	
    @Test
    public void testSkillWithNoValues() {
        Skill s1 = new Skill();

        // validate the input
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Skill>> violations = validator.validate(s1, New.class, Existing.class);
        assertEquals(3, violations.size());
    }
    
    @Test
    public void testValidExistingSkill() {
    	Skill s1 = new Skill(3, "Swift", true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, Existing.class);
    	assertEquals(0, violations.size());
    }
    
    @Test
    public void testValidNewSkill() {
    	Skill s1 = new Skill(0, "Swift", true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, New.class);
    	assertEquals(0, violations.size());
    }
    
    @Test
    public void testEmptyName() {
    	Skill s1 = new Skill(3, "", true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, Existing.class);
    	assertEquals(1, violations.size());
    }
    
    @Test
    public void testExcessivelyLargeName() {
    	//Generating an excessively large string
    	StringBuilder namebuilder = new StringBuilder();
    	for (int i = 0; i < 200; i++) {
    		namebuilder.append("a");
    	}
    	
    	String name = namebuilder.toString();
    	Skill s1 = new Skill(3, name, true);
    	
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	Validator validator = factory.getValidator();
    	Set<ConstraintViolation<Skill>> violations = validator.validate(s1, Existing.class);
    	assertEquals(1, violations.size());
    }
}
