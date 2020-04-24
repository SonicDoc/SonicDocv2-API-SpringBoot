package com.sonicdoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Doctor;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Integer>{
	public List<Doctor> findByApellidos(String apellidos);	
}
