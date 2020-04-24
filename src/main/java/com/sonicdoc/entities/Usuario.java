package com.sonicdoc.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

//property expressions
@Entity
@Table(name="usuario")
@Data
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	//ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//PASSWORD
	@Column(name="password",nullable=true)
	@Size(min = 7, max = 12)
	private String password;
	
	// CORREO
	@Column(name="email",nullable=false)
	@Email(message="Debe ser un email válido")
	private String email;
	
	//TELEFONO
	@Column(name="username",nullable=true,length=10)
	private String username;
		
	// DNI
	@Column(name="dni",nullable=false,length=8)
	private String dni;
	
	// CONSULTORIO
	@JsonBackReference
	@OneToMany(mappedBy="usuario")
	private List<Paciente> pacientes;
}
