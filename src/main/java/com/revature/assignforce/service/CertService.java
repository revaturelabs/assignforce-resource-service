package com.revature.assignforce.service;

import com.revature.assignforce.beans.Cert;

import java.util.List;
import java.util.Optional;

public interface CertService {

	List<Cert> getAll();
	Optional<Cert> findById(int id);
	Cert update(Cert c);
	Cert create(Cert c);
	void delete(int id);
	
}
