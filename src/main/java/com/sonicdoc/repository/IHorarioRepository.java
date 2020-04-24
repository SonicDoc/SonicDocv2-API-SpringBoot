package com.sonicdoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Horario;

@Repository
public interface IHorarioRepository extends JpaRepository<Horario,Integer> {
	@Query("FROM Horario h where h.doctor.id = :doctorId")
	public List<Horario> findByDoctorId(@Param("doctorId") int doctorId);
}
