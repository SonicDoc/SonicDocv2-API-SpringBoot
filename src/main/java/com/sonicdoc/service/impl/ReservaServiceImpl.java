package com.sonicdoc.service.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sonicdoc.entities.Reserva;
import com.sonicdoc.repository.IReservaRepository;
import com.sonicdoc.service.IReservaService;

@Service
@Transactional(readOnly=true)
public class ReservaServiceImpl implements IReservaService{

	@Autowired
	private IReservaRepository reservaRepository;

	@Override
	@Transactional
	public Reserva save(Reserva t) throws Exception {
		return reservaRepository.save(t);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) throws Exception {
		reservaRepository.deleteById(id);
	}

	@Override
	public Optional<Reserva> findById(Integer id) throws Exception {
		return reservaRepository.findById(id);
	}

	@Override
	public List<Reserva> findAll() throws Exception {
		return reservaRepository.findAll();
	}

	@Override
	public List<Reserva> findByPacienteId(int pacienteId) throws Exception {
		return reservaRepository.findByPacienteId(pacienteId);
	}

	@Override
	public List<Reserva> findByFecha(LocalDate fechaCita) throws Exception {
		return reservaRepository.findByFecha(fechaCita);
	}
	
	

}
