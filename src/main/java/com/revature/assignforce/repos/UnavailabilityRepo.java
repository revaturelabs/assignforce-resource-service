package com.revature.assignforce.repos;

import com.revature.assignforce.beans.LocationUnavailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailabilityRepo extends JpaRepository<LocationUnavailability, Integer> {

}
