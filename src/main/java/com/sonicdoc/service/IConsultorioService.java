package com.sonicdoc.service;

import java.util.List;

import com.sonicdoc.entities.Consultorio;

public interface IConsultorioService extends ICrudService<Consultorio>{
	public List<Consultorio> findByEspecialidadId(int especialidadId) throws Exception;
	public List<Consultorio> findByDoctorId(int doctorId) throws Exception;
	public List<Consultorio> findBySedeId(int sedeId) throws Exception;
}
