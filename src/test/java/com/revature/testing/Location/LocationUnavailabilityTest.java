package com.revature.testing.Location;

import com.revature.assignforce.beans.LocationUnavailability;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationUnavailabilityTest {

    @Configuration
    static class UnavailabilityTestContextConfiguration{
        @Bean
        public LocationUnavailability Unavailability(){ return new LocationUnavailability();}
    }

    @Test
    public void UnavailabilityTest1() {
        LocationUnavailability u = new LocationUnavailability();
        assertNotNull(u);
    }

    @Test
    public void getSetIdTest(){
        int id = 1;
        LocationUnavailability u = new LocationUnavailability();
        u.setId(id);
        assertTrue(u.getId() == id);
    }

    @Test
    public void getSetDescriptionTest(){
        String desc = "Already booked";
        LocationUnavailability u = new LocationUnavailability();
        u.setDescription(desc);
        assertTrue(u.getDescription().equals(desc));
    }

    @Test
    public void getSetStartDateTest(){
        LocationUnavailability u = new LocationUnavailability();
        LocalDate date = LocalDate.parse("2018-06-11");
        u.setStartDate(date);
        assertTrue(u.getStartDate().isEqual(LocalDate.parse("2018-06-11")));
    }

    @Test
    public void getSetEndDateTest(){
        LocationUnavailability u = new LocationUnavailability();
        LocalDate date = LocalDate.parse("2018-08-17");
        u.setEndDate(date);
        assertTrue(u.getEndDate().isEqual(LocalDate.parse("2018-08-17")));
    }

    @Test
    public void getSetRoomTest() {
        int room = 1402;
        LocationUnavailability u = new LocationUnavailability();
        u.setRoom(room);
        assertTrue(u.getRoom() == room);
    }
}
