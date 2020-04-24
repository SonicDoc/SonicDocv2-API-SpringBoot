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

import com.sonicdoc.entities.Especialidad;
import com.sonicdoc.service.IEspecialidadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/especialidades")
@Api(tags="Especialidades",value="Servicio Api de Especialidades")
public class EspecialidadesController {
	
	@Autowired
	private IEspecialidadService EspecialidadService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Especialidades", notes="Servicio para listar todos los Especialidades")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Especialidades encontrados"),
			@ApiResponse(code=404, message="Especialidades no encontrados")
	})	
	public ResponseEntity<List<Especialidad>> findAll(){
		try {
			List<Especialidad> Especialidades = new ArrayList<>();
			Especialidades = EspecialidadService.findAll();
			return new ResponseEntity<List<Especialidad>>(Especialidades, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Especialidad>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Especialidad por Id", notes="Servicio para buscar un Especialidad por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Especialidad encontrado"),
			@ApiResponse(code=404, message="Especialidad no encontrado")
	})
	public ResponseEntity<Especialidad> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Especialidad> Especialidad= EspecialidadService.findById(id);
			if(!Especialidad.isPresent()) {
				return new ResponseEntity<Especialidad>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Especialidad>(Especialidad.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Especialidad>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Especialidad", notes="Servicio para crear un nuevo Especialidad")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Especialidad creado correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Especialidad> insertEspecialidad(@Valid @RequestBody Especialidad Especialidad){
		try {
			Especialidad EspecialidadNew = new Especialidad();
			EspecialidadNew = EspecialidadService.save(Especialidad);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(EspecialidadNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Especialidad>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Especialidad", notes="Servicio para actualizar una especialidad")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Especialidad actualizado correctamente"),
			@ApiResponse(code=404, message="Especialidad no encontrado")
	})
	public ResponseEntity<Especialidad> updateEspecialidad(@Valid @RequestBody Especialidad Especialidad){
		try {
			EspecialidadService.save(Especialidad);			
			return new ResponseEntity<Especialidad>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Especialidad>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Especialidad", notes="Servicio para eliminar un Especialidad")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Especialidad eliminada correctamente"),
			@ApiResponse(code=404, message="Especialidad no encontrada")
	})
	public ResponseEntity<Especialidad> deleteEspecialidad(@PathVariable("id") Integer id){
		try {
			Optional<Especialidad> especialidad= EspecialidadService.findById(id);
			if(!especialidad.isPresent()) {
				return new ResponseEntity<Especialidad>(HttpStatus.NOT_FOUND);
			}
			EspecialidadService.deleteById(id);
			return new ResponseEntity<Especialidad>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Especialidad>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

