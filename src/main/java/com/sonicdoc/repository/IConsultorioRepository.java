package com.sonicdoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Consultorio;

@Repository
public interface IConsultorioRepository extends JpaRepository<Consultorio,Integer>{
	@Query("FROM Consultorio c where c.especialidad.id = :especialidadId")
	public List<Consultorio> findByEspecialidadId(@Param("especialidadId") int especialidadId);
	
	@Query("FROM Consultorio c where c.doctor.id = :doctorId")
	public List<Consultorio> findByDoctorId(@Param("doctorId") int doctorId);
	
	@Query("FROM Consultorio c where c.sede.id = :sedeId")
	public List<Consultorio> findBySedeId(@Param("sedeId") int sedeId);
}
