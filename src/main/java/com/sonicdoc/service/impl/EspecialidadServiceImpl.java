package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Especialidad;
import com.sonicdoc.repository.IEspecialidadRepository;
import com.sonicdoc.service.IEspecialidadService;

@Service
@Transactional(readOnly=true)
public class EspecialidadServiceImpl implements IEspecialidadService{

	@Autowired
	private IEspecialidadRepository especialidadRepository;
	
	@Override
	@Transactional
	public Especialidad save(Especialidad t) throws Exception {
		return especialidadRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		especialidadRepository.deleteById(id);
	}

	@Override
	public Optional<Especialidad> findById(Integer id) throws Exception {
		return especialidadRepository.findById(id);
	}

	@Override
	public List<Especialidad> findAll() throws Exception {
		return especialidadRepository.findAll();
	}

}
