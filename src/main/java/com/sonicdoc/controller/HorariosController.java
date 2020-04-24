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

import com.sonicdoc.entities.Horario;
import com.sonicdoc.service.IHorarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/horarios")
@Api(tags="Horarios",value="Servicio Api de Horarios")
public class HorariosController {
	
	@Autowired
	private IHorarioService horarioService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Horarios", notes="Servicio para listar todos los Horarios")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Horarios encontrados"),
			@ApiResponse(code=404, message="Horarios no encontrados")
	})	
	public ResponseEntity<List<Horario>> findAll(){
		try {
			List<Horario> Horarios = new ArrayList<>();
			Horarios = horarioService.findAll();
			return new ResponseEntity<List<Horario>>(Horarios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Horario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Horario por Id", notes="Servicio para buscar un Horario por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Horario encontrado"),
			@ApiResponse(code=404, message="Horario no encontrado")
	})
	public ResponseEntity<Horario> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Horario> Horario= horarioService.findById(id);
			if(!Horario.isPresent()) {
				return new ResponseEntity<Horario>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Horario>(Horario.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Horario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Horario", notes="Servicio para crear un nuevo Horario")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Horario creado correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Horario> insertHorario(@Valid @RequestBody Horario Horario){
		try {
			Horario HorarioNew = new Horario();
			HorarioNew = horarioService.save(Horario);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(HorarioNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Horario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Horario", notes="Servicio para actualizar un horario")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Horario actualizado correctamente"),
			@ApiResponse(code=404, message="Horario no encontrado")
	})
	public ResponseEntity<Horario> updateHorario(@Valid @RequestBody Horario Horario){
		try {
			horarioService.save(Horario);			
			return new ResponseEntity<Horario>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Horario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Horario", notes="Servicio para eliminar un Horario")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Horario eliminada correctamente"),
			@ApiResponse(code=404, message="Horario no encontrado")
	})
	public ResponseEntity<Horario> deleteHorario(@PathVariable("id") Integer id){
		try {
			Optional<Horario> horario = horarioService.findById(id);
			if(!horario.isPresent()) {
				return new ResponseEntity<Horario>(HttpStatus.NOT_FOUND);
			}
			horarioService.deleteById(id);
			return new ResponseEntity<Horario>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Horario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//BUSCAR POR DOCTOR
	@GetMapping(value="/searchByDoctor/{doctorId}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar horarios por Doctor", notes="Servicio para buscar horarios por doctor")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Horarios encontrados"),
			@ApiResponse(code=404, message="Horariosno encontrados")
	})	
	public ResponseEntity<List<Horario>> findByDoctorId(@PathVariable("doctorId") int doctorId){
		try {
			List<Horario> horarios = new ArrayList<>(); 
			horarios = horarioService.findByDoctorId(doctorId);
			return new ResponseEntity<List<Horario>>(horarios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Horario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
