package com.sonicdoc.service;

import java.util.List;

import com.sonicdoc.entities.Paciente;

public interface IPacienteService extends ICrudService<Paciente>{
	public Paciente findByDni(String dni) throws Exception;
	public List<Paciente> findByApellidos(String apellidos) throws Exception;
}
