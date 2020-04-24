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

import com.sonicdoc.entities.Resena;
import com.sonicdoc.service.IResenaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/resenas")
@Api(tags="Resenas",value="Servicio Api de Resenas")
public class ResenasController {
	
	@Autowired
	private IResenaService resenaService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Resenas", notes="Servicio para listar todos los Resenas")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Resenas encontrados"),
			@ApiResponse(code=404, message="Resenas no encontrados")
	})	
	public ResponseEntity<List<Resena>> findAll(){
		try {
			List<Resena> Resenas = new ArrayList<>();
			Resenas = resenaService.findAll();
			return new ResponseEntity<List<Resena>>(Resenas, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Resena>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Resena por Id", notes="Servicio para buscar un Resena por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Resena encontrado"),
			@ApiResponse(code=404, message="Resena no encontrado")
	})
	public ResponseEntity<Resena> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Resena> Resena= resenaService.findById(id);
			if(!Resena.isPresent()) {
				return new ResponseEntity<Resena>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Resena>(Resena.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Resena>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Resena", notes="Servicio para crear un nuevo Resena")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Resena creado correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Resena> insertResena(@Valid @RequestBody Resena Resena){
		try {
			Resena ResenaNew = new Resena();
			ResenaNew = resenaService.save(Resena);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(ResenaNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Resena>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Resena", notes="Servicio para actualizar una resena")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Resena actualizado correctamente"),
			@ApiResponse(code=404, message="Resena no encontrado")
	})
	public ResponseEntity<Resena> updateResena(@Valid @RequestBody Resena Resena){
		try {
			resenaService.save(Resena);			
			return new ResponseEntity<Resena>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Resena>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Resena", notes="Servicio para eliminar una Resena")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Resena eliminada correctamente"),
			@ApiResponse(code=404, message="Resena no encontrada")
	})
	public ResponseEntity<Resena> deleteResena(@PathVariable("id") Integer id){
		try {
			Optional<Resena> resena = resenaService.findById(id);
			if(!resena.isPresent()) {
				return new ResponseEntity<Resena>(HttpStatus.NOT_FOUND);
			}
			resenaService.deleteById(id);
			return new ResponseEntity<Resena>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Resena>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//BUSCAR POR RESERVA
	@GetMapping(value="/searchByReserva/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Resena por Id Reserva", notes="Servicio para buscar un Resena por Reserva Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Resenas encontradas"),
			@ApiResponse(code=404, message="Resenas no encontradas")
	})
	public ResponseEntity<List<Resena>> findByReservaId(@PathVariable("id") Integer reservaId){
		try {
			List<Resena> resenas = resenaService.findByReservaId(reservaId);
			return new ResponseEntity<List<Resena>>(resenas, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Resena>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
