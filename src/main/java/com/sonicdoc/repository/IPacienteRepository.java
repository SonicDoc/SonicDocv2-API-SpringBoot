package com.sonicdoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente,Integer>{
	public Paciente findByDni(String dni);
	public List<Paciente> findByApellidos(String apellidos);	
}
