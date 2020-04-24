package com.sonicdoc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Reserva;

@Repository
public interface IReservaRepository  extends JpaRepository<Reserva,Integer> {
	@Query("FROM Reserva r where r.paciente.id = :pacienteId")
	public List<Reserva>findByPacienteId(@Param("pacienteId") int pacienteId);
	
	public List<Reserva>findByFecha(LocalDate fechaCita);
}
