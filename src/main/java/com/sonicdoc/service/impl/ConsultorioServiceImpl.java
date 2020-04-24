package com.sonicdoc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Consultorio;
import com.sonicdoc.repository.IConsultorioRepository;
import com.sonicdoc.service.IConsultorioService;

@Service
@Transactional(readOnly=true)
public class ConsultorioServiceImpl implements IConsultorioService{

	@Autowired
	private IConsultorioRepository consultorioRepository;

	@Override
	public Consultorio save(Consultorio t) throws Exception {
		return consultorioRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		consultorioRepository.deleteById(id);
	}

	@Override
	public Optional<Consultorio> findById(Integer id) throws Exception {
		return consultorioRepository.findById(id);
	}

	@Override
	public List<Consultorio> findAll() throws Exception {
		return consultorioRepository.findAll();
	}

	@Override
	public List<Consultorio> findByEspecialidadId(int especialidadId) throws Exception {
		return consultorioRepository.findByEspecialidadId(especialidadId);
	}

	@Override
	public List<Consultorio> findBySedeId(int sedeId) throws Exception {
		return consultorioRepository.findBySedeId(sedeId);
	}

	@Override
	public List<Consultorio> findByDoctorId(int doctorId) throws Exception {
		return consultorioRepository.findByDoctorId(doctorId);
	}

}
