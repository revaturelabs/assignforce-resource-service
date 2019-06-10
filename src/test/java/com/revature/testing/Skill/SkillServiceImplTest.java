package com.revature.testing.Skill;

import com.amazonaws.services.sns.AmazonSNS;
import com.revature.assignforce.beans.Skill;
import com.revature.assignforce.repos.SkillRepo;
import com.revature.assignforce.service.SkillService;
import com.revature.assignforce.service.SkillServiceImpl;
import com.revature.assignforce.service.SkillsSNSNotificationSender;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillServiceImplTest {

    static AmazonSNS amazonSNS;
    static NotificationMessagingTemplate notificationMessagingTemplate;

    @BeforeClass
    public static void init() {
        amazonSNS = Mockito.mock(AmazonSNS.class);
        notificationMessagingTemplate = Mockito.mock(NotificationMessagingTemplate.class);
    }

    // Add this section for application context.
    @Configuration
    static class SkillServiceTestContextConfiguration {

        @Bean
        public SkillsSNSNotificationSender skillsSNSNotificationSender() { return new SkillsSNSNotificationSender(notificationMessagingTemplate); }

        @Bean
        public SkillService skillService() {
            return new SkillServiceImpl();
        }

        @Bean
        public SkillRepo SkillRepository() {
            return Mockito.mock(SkillRepo.class);
        }
    }

    @Autowired
    private SkillService skillService;
    @Autowired
    private SkillRepo skillRepo;

    @Autowired
    private SkillsSNSNotificationSender skillsSNSNotificationSender;

    // This function test whether the getId() returns the correct id associated with the skill
    // object and that the HTTP status is 'ok' if the getId() method corresponds with skill
    // object's id
    @Test
    public void getSkillByIdTest() {
        Skill s1 = new Skill(2, "Spring", true);
        Optional<Skill> op1 = Optional.ofNullable(s1);
        Mockito.when(skillRepo.findById(2)).thenReturn(op1);
        Optional<Skill> opTest = skillService.getSkillById(2);
        assertTrue(opTest.get().getId() == 2);
    }

    // This function test if the getAll() method gets all the skill objects that are saved in the Database
    @Test
    public void getAllTest() {
        Skill s1 = new Skill(2, "Spring", true);
        Skill s2 = new Skill(1, "Java", true);
        Skill s3 = new Skill(4, "PHP", false);
        List<Skill> skillList = new ArrayList<Skill>();
        skillList.add(s1);
        skillList.add(s2);
        skillList.add(s3);
        Mockito.when(skillRepo.findAll()).thenReturn(skillList);
        List<Skill> testList = skillService.getAll();
        assertTrue(testList.size() == 3);
    }

    //
    @Test
    public void createSkillTest() {
        Skill s1 = new Skill(5, "Hibernate", true);
        Mockito.when(skillRepo.save(s1)).thenReturn(s1);
        Skill testSkill = skillService.createSkill(s1);
        assertTrue(testSkill.getId() == 5);
    }

    // This function test to see if the updateSkill() method updates the skill from not active, to
    // active returns false if it does not change
    @Test
    public void updateSkillTest() {
        Skill s1 = new Skill(5, "Hibernate", true);
        s1.setIsActive(false);
        Mockito.when(skillRepo.save(s1)).thenReturn(s1);
        Skill testSkill = skillService.updateSkill(s1);
        assertFalse(testSkill.getIsActive());
    }

    // This function test to see if the deleteById() method deletes the associated skill obeject with the
    // corresponding id, returns false if it does not delete
    @Test
    public void deleteSkillTest() {
        Mockito.doNothing().when(skillRepo).deleteById(7);
        skillService.deleteSkill(7);
        Mockito.verify(skillRepo, times(1)).deleteById(7);
    }
}
