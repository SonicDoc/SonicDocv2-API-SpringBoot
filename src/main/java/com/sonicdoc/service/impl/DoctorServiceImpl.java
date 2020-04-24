package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Doctor;
import com.sonicdoc.repository.IDoctorRepository;
import com.sonicdoc.service.IDoctorService;

@Service
@Transactional(readOnly=true)
public class DoctorServiceImpl implements IDoctorService{

	@Autowired
	private IDoctorRepository doctorRepository;
	
	@Override
	@Transactional
	public Doctor save(Doctor t) throws Exception {
		return doctorRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		doctorRepository.deleteById(id);
	}

	@Override
	public Optional<Doctor> findById(Integer id) throws Exception {
		return doctorRepository.findById(id);
	}

	@Override
	public List<Doctor> findAll() throws Exception {
		return doctorRepository.findAll();
	}
	
	@Override
	public List<Doctor> findByApellidos(String apellidos) throws Exception {
		return doctorRepository.findByApellidos(apellidos);
	}

}
