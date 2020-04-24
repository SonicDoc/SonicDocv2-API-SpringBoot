package com.sonicdoc.service;

import java.time.LocalDate;
import java.util.List;

import com.sonicdoc.entities.Reserva;

public interface IReservaService extends ICrudService<Reserva> {
	public List<Reserva>findByPacienteId(int pacienteId) throws Exception;
	public List<Reserva>findByFecha(LocalDate fechaCita) throws Exception;
}
