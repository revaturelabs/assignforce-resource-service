package com.revature.assignforce.repos;

import com.revature.assignforce.beans.Cert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertRepo extends JpaRepository<Cert, Integer>{

}
