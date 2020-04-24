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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


//No hacen falta queries x ahora
@Entity
@Table(name="especialidad")
@Data
public class Especialidad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//NOMBRE
	@Column(name="nombre",nullable=false,length=20)
	private String nombre;
	
	// DESCRIPCION
	@Column(name="descripcion",nullable=true,length=250)
	private String descripcion;
	// ---------- HAS ONE	
	
	// ---------- HAS MANY
	// CONSULTORIO
	@JsonBackReference
	@OneToMany(mappedBy="especialidad")
	private List<Consultorio> consultorios;
}
