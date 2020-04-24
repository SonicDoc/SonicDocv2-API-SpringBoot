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

import com.sonicdoc.entities.Consultorio;
import com.sonicdoc.service.IConsultorioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/consultorios")
@Api(tags="Consultorios",value="Servicio Api de Consultorios")
public class ConsultoriosController {

	@Autowired
	private IConsultorioService consultorioService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Consultorios", notes="Servicio para listar todos los consultorios")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorios encontrados"),
			@ApiResponse(code=404, message="Consultorios no encontrados")
	})	
	public ResponseEntity<List<Consultorio>> findAll(){
		try {
			List<Consultorio> consultorios = new ArrayList<>();
			consultorios = consultorioService.findAll();
			return new ResponseEntity<List<Consultorio>>(consultorios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Consultorio>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Consultorio por Id", notes="Servicio para buscar un consultorio por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorio encontrado"),
			@ApiResponse(code=404, message="Consultorio no encontrado")
	})
	public ResponseEntity<Consultorio> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Consultorio> consultorio= consultorioService.findById(id);
			if(!consultorio.isPresent()) {
				return new ResponseEntity<Consultorio>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Consultorio>(consultorio.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Consultorio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	//MAS GETs AL FINAL
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Consultorio", notes="Servicio para crear un nuevo consultorio")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorio creado correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Consultorio> insertConsultorio(@Valid @RequestBody Consultorio consultorio){
		try {
			Consultorio consultorioNew = new Consultorio();
			consultorioNew = consultorioService.save(consultorio);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(consultorioNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Consultorio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Consultorio", notes="Servicio para actualizar un consultorio")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorio actualizado correctamente"),
			@ApiResponse(code=404, message="Consultorio no encontrado")
	})
	public ResponseEntity<Consultorio> updateConsultorio(@Valid @RequestBody Consultorio consultorio){
		try {
			consultorioService.save(consultorio);			
			return new ResponseEntity<Consultorio>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Consultorio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Consultorio", notes="Servicio para eliminar un consultorio")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorio eliminado correctamente"),
			@ApiResponse(code=404, message="Consultorio no encontrado")
	})
	public ResponseEntity<Consultorio> deleteConsultorio(@PathVariable("id") Integer id){
		try {
			Optional<Consultorio> consultorio= consultorioService.findById(id);
			if(!consultorio.isPresent()) {
				return new ResponseEntity<Consultorio>(HttpStatus.NOT_FOUND);
			}
			consultorioService.deleteById(id);
			return new ResponseEntity<Consultorio>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Consultorio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//BUSCAR POR ESPECIALIDAD
	@GetMapping(value="/searchByEspecialidad/{especialidadId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar consultorios por Especialidad", notes="Servicio para buscar consultorios por especialidad")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorios encontrados"),
			@ApiResponse(code=404, message="Consultorios no encontrados")
	})	
	public ResponseEntity<List<Consultorio>> findByEspecialidadId(@PathVariable("especialidadId") int especialidadId){
		try {
			List<Consultorio> consultorios = new ArrayList<>(); 
			consultorios = consultorioService.findByDoctorId(especialidadId);
			return new ResponseEntity<List<Consultorio>>(consultorios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Consultorio>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//BUSCAR POR DOCTOR
	@GetMapping(value="/searchByDoctor/{doctorId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar consultorios por Doctor", notes="Servicio para buscar consultorios por doctor")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorios encontrados"),
			@ApiResponse(code=404, message="Consultorios no encontrados")
	})	
	public ResponseEntity<List<Consultorio>> findByDoctorId(@PathVariable("doctorId") int doctorId){
		try {
			List<Consultorio> consultorios = new ArrayList<>();
			consultorios = consultorioService.findByDoctorId(doctorId);
			return new ResponseEntity<List<Consultorio>>(consultorios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Consultorio>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//BUSCAR POR SEDE
	@GetMapping(value="/searchBySede/{sedeId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar consultorios por Sede", notes="Servicio para buscar consultorios por sede")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Consultorios encontrados"),
			@ApiResponse(code=404, message="Consultorios no encontrados")
	})	
	public ResponseEntity<List<Consultorio>> findBySedeId(@PathVariable("sedeId") int sedeId){
		try {
			List<Consultorio> consultorios = new ArrayList<>();
			consultorios = consultorioService.findBySedeId(sedeId);
			return new ResponseEntity<List<Consultorio>>(consultorios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Consultorio>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
