package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Horario;
import com.sonicdoc.repository.IHorarioRepository;
import com.sonicdoc.service.IHorarioService;

@Service
@Transactional(readOnly=true)
public class HorarioServiceImpl implements IHorarioService {

	@Autowired
	private IHorarioRepository horarioRepository;

	@Override
	@Transactional
	public Horario save(Horario t) throws Exception {
		return  horarioRepository.save(t) ;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		horarioRepository.deleteById(id);;
	}

	@Override
	public Optional<Horario> findById(Integer id) throws Exception {
		return horarioRepository.findById(id);
	}

	@Override
	public List<Horario> findAll() throws Exception {
		return horarioRepository.findAll();
	}

	@Override
	public List<Horario> findByDoctorId(int doctorId) throws Exception {
		return horarioRepository.findByDoctorId(doctorId);
	}

}
