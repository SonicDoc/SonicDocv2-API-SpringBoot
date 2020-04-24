package com.sonicdoc.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

//queries done + property expressions
@Entity
@Table(name="reserva")
@Data
public class Reserva implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	// HORA DE CREACION
	@Column(name="fechaHoraCreacion", nullable=true, insertable = false)
	@CreationTimestamp
	@Basic
	private LocalDateTime fechaHoraCreacion;
	
	// FECHA DE RESERVA
	@Column(name="fechaReserva",nullable=false)
	@Basic
	private LocalDate fecha;
	
	// HORA DE RESERVA
	@Column(name="horaReserva",nullable=false)
	@Basic
	private LocalTime hora;
	// ---------- HAS ONE
	
	//CHECK IF DB BREAKS. DELETION FLAG TO NOT RECREATE DATA IF USER WANTS TO RESTORE APPOINTMENT
	@Column(name="estado", nullable = false)
	private boolean estado = true;
	
	
	//RESERVA PACIENTE
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	@JoinColumn(nullable=false, referencedColumnName = "id")
	private Paciente paciente;
	
	//RESERVA DOCTOR
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	@JoinColumn(nullable=false, referencedColumnName = "id")
	private Doctor doctor;
	
	// ---------- HAS MANY
	// CONSULTORIO
	@JsonBackReference
	@OneToMany(mappedBy="reserva")
	private List<Resena> resenas;
}
