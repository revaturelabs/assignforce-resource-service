package com.revature.assignforce.service;

import com.revature.assignforce.beans.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
	
	List<Room> getAll();

	Optional<Room> findById(int id);

	Room update(Room t);

	Room create(Room t);

	void delete(int id);

}
