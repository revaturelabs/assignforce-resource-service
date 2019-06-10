package com.revature.testing.Location;

import com.revature.assignforce.beans.LocationUnavailability;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.repos.RoomRepo;
import com.revature.assignforce.repos.UnavailabilityRepo;
import com.revature.assignforce.service.RoomService;
import com.revature.assignforce.service.RoomServiceImpl;
import com.revature.assignforce.service.UnavailabilityService;
import com.revature.assignforce.service.UnavailabilityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationUnavailabilityServiceImplTest {

    @Configuration
    static class UnavailabilityServiceTestContextConfiguration{
        @Bean
        public RoomService RoomService(){return new RoomServiceImpl();}
        @Bean
        public RoomRepo RoomRepository(){return Mockito.mock(RoomRepo.class);}
        @Bean
        public UnavailabilityService UnavailabilityService(){return new UnavailabilityServiceImpl();}
        @Bean
        public UnavailabilityRepo UnavailabilityRepository(){return Mockito.mock(UnavailabilityRepo.class);}
    }

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private UnavailabilityService unavailabilityService;
    @Autowired
    private UnavailabilityRepo unavailabilityRepo;

    @Test
    public void getAllTest() {
        Room r1 = new Room(5,"Planning", 6);
        Room r2 = new Room(4,"Planning", 3);
        LocationUnavailability u1 = new LocationUnavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        LocationUnavailability u2 = new LocationUnavailability(3, ".net", LocalDate.parse("2018-08-11"), LocalDate.parse("2018-06-04"), 4);
        List<LocationUnavailability> locationUnavailabilityList = new ArrayList<>();
        locationUnavailabilityList.add(u1);
        locationUnavailabilityList.add(u2);
        Mockito.when(unavailabilityRepo.findAll()).thenReturn(locationUnavailabilityList);
        List<LocationUnavailability> testList = unavailabilityService.getAll();
        assertEquals(2, testList.size());
    }

    @Test
    public void findByIdTest() {
        LocationUnavailability u1 = new LocationUnavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        Optional<LocationUnavailability> op1 = Optional.ofNullable(u1);
        Mockito.when(unavailabilityRepo.findById(4)).thenReturn(op1);
        Optional<LocationUnavailability> lTest = unavailabilityService.findById(4);
        assertEquals("Java", lTest.get().getDescription());
    }

    @Test
    public void updateTest() {
        LocationUnavailability u1 = new LocationUnavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        u1.setDescription(".net");
        Mockito.when(unavailabilityRepo.save(u1)).thenReturn(u1);
        LocationUnavailability lTest = unavailabilityService.update(u1);
        assertEquals(".net", lTest.getDescription());
    }

    @Test
    public void createTest() {
        LocationUnavailability u1 = new LocationUnavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        Mockito.when(unavailabilityRepo.save(u1)).thenReturn(u1);
        LocationUnavailability lTest = unavailabilityService.create(u1);
        assertEquals(1, lTest.getId());
    }

    @Test
    public void deleteTest() {
        Mockito.doNothing().when(unavailabilityRepo).deleteById(8);
        unavailabilityService.delete(8);
        Optional<LocationUnavailability> opTest = unavailabilityService.findById(8);
        assertFalse(opTest.isPresent());
    }

//    @Test
//    public void addUnavailabilityTest(){
//        LocationUnavailability u1 = new LocationUnavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 4);
//        Room r1 = new Room(4, "Staging", 2);
//        Optional<Room> op1 = Optional.ofNullable(r1);
//        Mockito.when(roomRepository.findById(4)).thenReturn(op1);
//        LocationUnavailability test = u1;
//        test.setRoomObject(roomRepository.findById(4).orElse(null));
//        Mockito.when(unavailabilityService.addUnavailability(u1, 4)).thenReturn(test);
//        Optional<LocationUnavailability> lTest = Optional.ofNullable(unavailabilityService.addUnavailability(u1, 4));
//        assertEquals(4, lTest.get().getRoomObject().getId());
//    }
//
//    @Test
//    public void updateUnavailabilityTest(){
//        LocationUnavailability u = new LocationUnavailability(1, "Test", LocalDate.parse("2018-07-16"), LocalDate.parse("2018-06-11"), 4);
//        Room r = new Room(4, "Staging", 2);
//        Optional<Room> rOpt = Optional.ofNullable(r);
//        Mockito.when(roomRepository.findById(4)).thenReturn(rOpt);
//        LocationUnavailability test = u;
//        test.setRoomObject(roomRepository.findById(4).orElse(null));
//        Mockito.when(unavailabilityService.updateUnavailability(u, 4)).thenReturn(test);
//        Optional<LocationUnavailability> testOpt = Optional.ofNullable(unavailabilityService.updateUnavailability(u, 4));
//        assertEquals(4, testOpt.get().getRoomObject().getId());
//    }
}
