package com.revature.assignforce.repos;

import com.revature.assignforce.beans.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {

}
