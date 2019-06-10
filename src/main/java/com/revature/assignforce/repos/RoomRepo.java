package com.revature.assignforce.repos;

import com.revature.assignforce.beans.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {

}
