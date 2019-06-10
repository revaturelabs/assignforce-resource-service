package com.revature.assignforce.service;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.repos.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepo roomRepo;

	@Override
	public List<Room> getAll() {
		return roomRepo.findAll();
	}

	@Override
	public Optional<Room> findById(int id) {
		return roomRepo.findById(id);
	}

	@Override
	public Room update(Room t) {
		return roomRepo.save(t);
	}

	@Override
	public Room create(Room t) {
		return roomRepo.save(t);
	}

	@Override
	public void delete(int id) {
		roomRepo.deleteById(id);
	}

}
