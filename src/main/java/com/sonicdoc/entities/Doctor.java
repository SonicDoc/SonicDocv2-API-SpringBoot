package com.sonicdoc.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

//Queries with property expressions
@Entity
@Table(name="doctor")
@Data
public class Doctor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	// NOMBRE
	@Column(name="nombres",nullable=false,length=100)
	private String nombres;
	
	// APELLIDO
	@Column(name="apellidos",nullable=false,length=100)
	private String apellidos;
	
	// BIO/DESC
	@Column(name="miniBio",nullable=true,length=50)
	private String miniBio;
	
	//Consultorio
	@JsonManagedReference
	@OneToOne(mappedBy = "doctor")
	private Consultorio consultorio;

	// ---------- HAS ONE
	
	// ---------- HAS MANY

	// HORARIOS
	@JsonBackReference
	@OneToMany(mappedBy="doctor")
	private List<Horario> horarios;
	
	// RESERVAS
	@JsonBackReference
	@OneToMany(mappedBy="doctor")
	private List<Reserva> reservas;
}
