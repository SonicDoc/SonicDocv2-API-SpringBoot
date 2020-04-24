package com.sonicdoc.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

//queries done
@Entity
@Table(name="consultorio")
@Data
public class Consultorio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// CORREO
	@Column(name="numero",nullable=true,length=5)
	private String numero;
	
	//DOCTOR
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="doctor_id", nullable = false, referencedColumnName = "id")
	@JsonIgnore  
    private Doctor doctor;
	
	//ESPECIALIDAD
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	@JoinColumn(nullable=false, referencedColumnName = "id")
	private Especialidad especialidad;
	
	//SEDE
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonIgnore
	@JoinColumn(nullable=false, referencedColumnName = "id")
	private Sede sede;
	
	// ---------- HAS ONE
}
