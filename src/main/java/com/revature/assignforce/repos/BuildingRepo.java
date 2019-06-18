package com.revature.assignforce.repos;

import com.revature.assignforce.beans.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends JpaRepository<Building, Integer> {

}
