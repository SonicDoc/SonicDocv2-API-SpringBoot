package com.sonicdoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Especialidad;

@Repository
public interface IEspecialidadRepository extends JpaRepository<Especialidad,Integer>{

}
