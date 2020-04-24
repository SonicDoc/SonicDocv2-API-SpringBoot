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

import com.sonicdoc.entities.Paciente;
import com.sonicdoc.service.IPacienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/pacientes")
@Api(tags="Pacientes",value="Servicio Api de Pacientes")
public class PacientesController {

	@Autowired
	private IPacienteService pacienteService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Pacientes", notes="Servicio para listar todos los pacientes")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Pacientes encontrados"),
			@ApiResponse(code=404, message="Pacientes no encontrados")
	})	
	public ResponseEntity<List<Paciente>> findAll(){
		try {
			List<Paciente> pacientes = new ArrayList<>();
			pacientes = pacienteService.findAll();
			return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Paciente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Paciente por Id", notes="Servicio para buscar un paciente por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Paciente encontrado"),
			@ApiResponse(code=404, message="Paciente no encontrado")
	})
	public ResponseEntity<Paciente> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Paciente> paciente= pacienteService.findById(id);
			if(!paciente.isPresent()) {
				return new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Paciente>(paciente.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Paciente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/searchByDni/{dni}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Paciente por Dni", notes="Servicio para buscar un Paciente por Dni")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Paciente encontrado"),
			@ApiResponse(code=404, message="Paciente no encontrado")
	})
	public ResponseEntity<Paciente> findByDni(@PathVariable("dni") String dni){
		try {
			Paciente paciente= pacienteService.findByDni(dni);
			if(paciente==null) {
				return new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Paciente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/searchByApellidos/{apellidos}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Pacientes por Apellidos", notes="Servicio para buscar todos los pacientes por apellidos")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Pacientes encontrados"),
			@ApiResponse(code=404, message="Pacientes no encontrados")
	})	
	public ResponseEntity<List<Paciente>> findByApellidos(@PathVariable("apellidos") String apellidos){
		try {
			List<Paciente> pacientes = new ArrayList<>(); 
			pacientes = pacienteService.findByApellidos(apellidos);
			return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Paciente>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Paciente", notes="Servicio para crear un nuevo paciente")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Paciente creado correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Paciente> insertPaciente(@Valid @RequestBody Paciente paciente){
		try {
			Paciente pacienteNew = new Paciente();
			pacienteNew = pacienteService.save(paciente);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(pacienteNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Paciente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Paciente", notes="Servicio para actualizar un paciente")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Paciente actualizado correctamente"),
			@ApiResponse(code=404, message="Paciente no encontrado")
	})
	public ResponseEntity<Paciente> updatePaciente(@Valid @RequestBody Paciente paciente){
		try {
			pacienteService.save(paciente);			
			return new ResponseEntity<Paciente>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Paciente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Paciente", notes="Servicio para eliminar un paciente")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Paciente eliminado correctamente"),
			@ApiResponse(code=404, message="Paciente no encontrado")
	})
	public ResponseEntity<Paciente> deletePaciente(@PathVariable("id") Integer id){
		try {
			Optional<Paciente> paciente= pacienteService.findById(id);
			if(!paciente.isPresent()) {
				return new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND);
			}
			pacienteService.deleteById(id);
			return new ResponseEntity<Paciente>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Paciente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
