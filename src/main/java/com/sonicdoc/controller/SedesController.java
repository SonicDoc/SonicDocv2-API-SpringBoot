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

import com.sonicdoc.entities.Sede;
import com.sonicdoc.service.ISedeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/sedes")
@Api(tags="Sedes",value="Servicio Api de Sedes")
public class SedesController {
	
	@Autowired
	private ISedeService sedeService;
	
	// ---------- LISTA ----------
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Sedes", notes="Servicio para listar todos los Sedes")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Sedes encontradas"),
			@ApiResponse(code=404, message="Sedes no encontradas")
	})	
	public ResponseEntity<List<Sede>> findAll(){
		try {
			List<Sede> sedes = new ArrayList<>();
			sedes = sedeService.findAll();
			return new ResponseEntity<List<Sede>>(sedes, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Sede>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// ---------- /LISTA ----------
	
	// ---------- BUSCAR POR ID ----------
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Sede por Id", notes="Servicio para buscar una Sede por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Sede encontrada"),
			@ApiResponse(code=404, message="Sede no encontrada")
	})
	public ResponseEntity<Sede> findById(@PathVariable("id") Integer id){
		try {
			Optional<Sede> Sede= sedeService.findById(id);
			if(!Sede.isPresent()) {
				return new ResponseEntity<Sede>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Sede>(Sede.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Sede>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// ---------- /BUSCAR POR ID ----------
	
	// ---------- CREAR ----------
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Sede", notes="Servicio para crear una nueva Sede")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Sede creada correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Sede> insertSede(@Valid @RequestBody Sede Sede){
		try {
			Sede SedeNew = new Sede();
			SedeNew = sedeService.save(Sede);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(SedeNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Sede>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// ---------- /CREAR ----------
				
				
	// ---------- ACTUALIZAR ----------
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Sede", notes="Servicio para actualizar una sede")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Sede actualizada correctamente"),
			@ApiResponse(code=404, message="Sede no encontrada")
	})
	public ResponseEntity<Sede> updateSede(@Valid @RequestBody Sede Sede){
		try {
			sedeService.save(Sede);			
			return new ResponseEntity<Sede>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Sede>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// ---------- /ACTUALIZAR ----------
				
	// ---------- ELIMINAR ----------
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Sede", notes="Servicio para eliminar una Sede")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Sede eliminada correctamente"),
			@ApiResponse(code=404, message="Sede no encontrada")
	})
	public ResponseEntity<Sede> deleteSede(@PathVariable("id") Integer id){
		try {
			Optional<Sede> sede= sedeService.findById(id);
			if(!sede.isPresent()) {
				return new ResponseEntity<Sede>(HttpStatus.NOT_FOUND);
			}
			sedeService.deleteById(id);
			return new ResponseEntity<Sede>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Sede>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// ---------- /ELIMINAR ----------
}
