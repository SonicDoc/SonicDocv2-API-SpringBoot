package com.sonicdoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer>{
	public Usuario findByDni(String dni);
}
