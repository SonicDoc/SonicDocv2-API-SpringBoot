package com.sonicdoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sonicdoc.entities.Sede;

@Repository
public interface ISedeRepository extends JpaRepository<Sede,Integer>{

}
