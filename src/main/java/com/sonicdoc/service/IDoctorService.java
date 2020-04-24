package com.sonicdoc.service;

import java.util.List;

import com.sonicdoc.entities.Doctor;

public interface IDoctorService extends ICrudService<Doctor>{
	public List<Doctor> findByApellidos(String apellidos) throws Exception;
}
