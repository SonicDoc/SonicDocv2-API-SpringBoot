package com.sonicdoc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sonicdoc.entities.Doctor;
import com.sonicdoc.service.IDoctorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/doctores")
@Api(tags="Doctores",value="Servicio Api de Doctores")
public class DoctoresController {

	@Autowired
	private IDoctorService doctorService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Doctores", notes="Servicio para listar todos los doctores")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Doctores encontrados"),
			@ApiResponse(code=404, message="Doctores no encontrados")
	})	
	public ResponseEntity<List<Doctor>> findAll(){
		try {
			List<Doctor> doctores = new ArrayList<>();
			doctores = doctorService.findAll();
			return new ResponseEntity<List<Doctor>>(doctores, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Doctor>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Doctor por Id", notes="Servicio para buscar un doctor por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Doctor encontrado"),
			@ApiResponse(code=404, message="Doctor no encontrado")
	})
	public ResponseEntity<Doctor> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Doctor> doctor= doctorService.findById(id);
			if(!doctor.isPresent()) {
				return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Doctor>(doctor.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Doctor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//GET BY APELLIDOS
	@GetMapping(value="/searchByApellidos/{apellidos}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Doctores por Apellidos", notes="Servicio para buscar doctores por apellidos")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Doctores encontrados"),
			@ApiResponse(code=404, message="Doctores no encontrados")
	})	
	public ResponseEntity<List<Doctor>> findByApellidos(@PathVariable("apellidos") String apellidos){
		try {
			List<Doctor> doctores = new ArrayList<>(); 
			doctores = doctorService.findByApellidos(apellidos);
			return new ResponseEntity<List<Doctor>>(doctores, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Doctor>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Doctor", notes="Servicio para crear un nuevo doctor")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Doctor creado correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Doctor> insertDoctor(@Valid @RequestBody Doctor doctor){
		try {
			Doctor doctorNew = new Doctor();
			doctorNew = doctorService.save(doctor);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(doctorNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Doctor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Doctor", notes="Servicio para actualizar un doctor")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Doctor actualizado correctamente"),
			@ApiResponse(code=404, message="Doctor no encontrado")
	})
	public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor){
		try {
			doctorService.save(doctor);			
			return new ResponseEntity<Doctor>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Doctor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Doctor", notes="Servicio para eliminar un doctor")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Doctor eliminado correctamente"),
			@ApiResponse(code=404, message="Doctor no encontrado")
	})
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") Integer id){
		try {
			Optional<Doctor> doctor= doctorService.findById(id);
			if(!doctor.isPresent()) {
				return new ResponseEntity<Doctor>(HttpStatus.NOT_FOUND);
			}
			doctorService.deleteById(id);
			return new ResponseEntity<Doctor>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Doctor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
