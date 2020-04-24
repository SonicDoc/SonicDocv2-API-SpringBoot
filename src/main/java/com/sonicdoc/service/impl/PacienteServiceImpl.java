package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Paciente;
import com.sonicdoc.repository.IPacienteRepository;
import com.sonicdoc.service.IPacienteService;

@Service
@Transactional(readOnly=true)
public class PacienteServiceImpl implements IPacienteService{

	@Autowired
	private IPacienteRepository pacienteRepository;
	
	@Override
	@Transactional
	public Paciente save(Paciente t) throws Exception {
		return pacienteRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		pacienteRepository.deleteById(id);
	}

	@Override
	public Optional<Paciente> findById(Integer id) throws Exception {
		return pacienteRepository.findById(id);
	}

	@Override
	public List<Paciente> findAll() throws Exception {
		return pacienteRepository.findAll();
	}
	
	@Override
	public Paciente findByDni(String dni) throws Exception {
		return pacienteRepository.findByDni(dni);
	}

	@Override
	public List<Paciente> findByApellidos(String apellidos) throws Exception {
		return pacienteRepository.findByApellidos(apellidos);
	}

}
