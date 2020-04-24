package com.sonicdoc.service;

import java.util.List;

import com.sonicdoc.entities.Horario;

public interface IHorarioService extends ICrudService<Horario> {
	public List<Horario> findByDoctorId(int doctorId) throws Exception;
}
