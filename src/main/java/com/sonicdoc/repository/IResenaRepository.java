package com.sonicdoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Resena;

@Repository
public interface IResenaRepository extends JpaRepository<Resena,Integer> {
	@Query("FROM Resena r where r.reserva.id = :reservaId")
	public List<Resena> findByReservaId(@Param("reservaId") int reservaId);
}
