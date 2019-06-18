package com.revature.testing.Location;

import com.revature.assignforce.beans.Location;
import com.revature.assignforce.repos.LocationRepo;
import com.revature.assignforce.service.LocationService;
import com.revature.assignforce.service.LocationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationServiceImplTest {

	@Configuration
	static class LocationServiceTestContextConfiguration {
	@Bean
	public LocationService LocationService() {
		return new LocationServiceImpl();
		}
	@Bean
	public LocationRepo LocationRepository() {
		return Mockito.mock(LocationRepo.class);
		}
	}
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private LocationRepo locationRepo;
	
	@Test
	public void getAllTest() {
		Location l1 = new Location(1, "Reston", "Reston", "VA", true);
		Location l2 = new Location(6, "Tampa", "Tampa", "FL", false);
		List<Location> locationList = new ArrayList<Location>();
		locationList.add(l1);
		locationList.add(l2);
		Mockito.when(locationRepo.findAll()).thenReturn(locationList);
		List<Location> testList = locationService.getAll();
		assertTrue(testList.size() == 2);
	}
	
	@Test
	public void findByIdTest() {
		String name = "Chicago";
		Location l = new Location(4, name, "Chicago", "IL", false);
		Optional<Location> op = Optional.ofNullable(l);
		Mockito.when(locationRepo.findById(4)).thenReturn(op);
		Optional<Location> lTest = locationService.findById(4);
		assertTrue(lTest.get().getName().equals(name));
	}
	
	@Test
	public void updateTest() {
		Location l = new Location(4, "Chicago", "Chicago", "IL", false);
		l.setIsActive(true);
		Mockito.when(locationRepo.save(l)).thenReturn(l);
		Location lTest = locationService.update(l);
		assertTrue(lTest.getIsActive());
	}
	
	@Test
	public void createTest() {
		int id = 5;
		Location l = new Location(id, "New York", "New York", "NY", true);
		Mockito.when(locationRepo.save(l)).thenReturn(l);
		Location lTest = locationService.create(l);
		assertTrue(lTest.getId() == id);
	}
	
	@Test
	public void deleteTest() {
		int id = 8;
		Mockito.doNothing().when(locationRepo).deleteById(id);
		locationService.delete(id);
		Optional<Location> opTest = locationService.findById(id);
		assertFalse(opTest.isPresent());
	}

}
