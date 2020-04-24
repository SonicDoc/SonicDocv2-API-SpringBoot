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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

//no queries
@Entity
@Table(name="sede")
@Data
public class Sede implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// NOMBRE según dirección, distrito o ninguno
	@Column(name="nombre",nullable=true,length=250)
	private String nombre;
	
	// DIRECCION
	@Column(name="direccion",nullable=false,length=250)
	private String direccion;
	// ---------- HAS ONE	
	
	// ---------- HAS MANY
	
	// CONSULTORIO
	@JsonBackReference
	@OneToMany(mappedBy="sede")
	private List<Consultorio> consultorios;
	
}
