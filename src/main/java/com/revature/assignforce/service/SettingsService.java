package com.revature.assignforce.service;

import com.revature.assignforce.beans.Settings;

import java.util.List;
import java.util.Optional;

public interface SettingsService {
	
	List<Settings> getAll();
	Optional<Settings> findById(int id);
	Settings update(Settings b);
	Settings create(Settings b);
	void delete(int id);
}
