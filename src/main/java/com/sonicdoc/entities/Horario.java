package com.sonicdoc.entities;


import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

//queries done
@Entity
@Table(name="horario")
@Data
public class Horario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//DISP. -> 1 SI ES DIA NORMAL Y 0 SI ES FERIADO O AL REVES
	@Column(name="disponibilidad",nullable=false)
	private boolean disponibilidad;
	
	// HORA DE INICIO DE TURNO
	@Column(name="horaInicio",nullable=true)
	@Basic
	private LocalTime horaInicio;
	
	// HORA DE FIN DE TURNO
	@Column(name="horaFin",nullable=true)
	@Basic
	private LocalTime horaFin;

	// ---------- HAS ONE
	// DOCTOR
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	@JoinColumn(nullable=false, referencedColumnName = "id")
	private Doctor doctor;

	// ---------- HAS MANY
    // NO
	
	/*
	 Un horario tiene un doctor mientras que un doctor tiene muchos horarios
	 */
}
