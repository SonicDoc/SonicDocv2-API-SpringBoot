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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

//Queries with property expressions
@Entity
@Table(name="paciente")
@Data
public class Paciente implements Serializable{
	
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
	
	// DNI
	@Column(name="dni",nullable=false,length=8)
	private String dni;

	// TELEFONO, CORREO Y DNI VA AL USUARIO
	/*
	@Column(name="telefono",nullable=true,length=10)
	private String telefono;
	@Column(name="correo",nullable=true,length=50)
	private String correo;
	@Column(name="dni",nullable=false,length=8)
	private String dni;
	*/
	
	// USUARIO ES EL PACIENTE?
	@Column(name="esUsuario",nullable=true,length=50, insertable = false)
	private Boolean esUsuario;
	
	//USUARIO ASOCIADO
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	@JoinColumn(nullable=false, referencedColumnName = "id")
	private Usuario usuario;
	
	// ---------- HAS ONE
	
	// ---------- HAS MANY
	// RESERVAS
	@JsonBackReference
	@OneToMany(mappedBy="paciente")
	private List<Reserva> reservas;
}
