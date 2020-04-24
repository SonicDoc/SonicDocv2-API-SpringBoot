package com.sonicdoc.controller;

import java.net.URI;
import java.time.LocalDate;
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

import com.sonicdoc.entities.Reserva;
import com.sonicdoc.service.IReservaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/reservas")
@Api(tags="Reservas",value="Servicio Api de Reservas")
public class ReservasController {
	
	@Autowired
	private IReservaService reservaService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Reservas", notes="Servicio para listar todos las Reservas")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Reservas encontradas"),
			@ApiResponse(code=404, message="Reservas no encontradas")
	})	
	public ResponseEntity<List<Reserva>> findAll(){
		try {
			List<Reserva> Reservas = new ArrayList<>();
			Reservas = reservaService.findAll();
			return new ResponseEntity<List<Reserva>>(Reservas, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Reserva>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Reserva por Id", notes="Servicio para buscar una Reserva por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Reserva encontrada"),
			@ApiResponse(code=404, message="Reserva no encontrada")
	})
	public ResponseEntity<Reserva> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Reserva> Reserva= reservaService.findById(id);
			if(!Reserva.isPresent()) {
				return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Reserva>(Reserva.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//BUSCAR POR PACIENTE
	@GetMapping(value="/searchByPaciente/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Reserva por Id paciente", notes="Servicio para buscar una Reserva por Id paciente")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Reservas encontradas"),
			@ApiResponse(code=404, message="Reservas no encontradas")
	})
	public ResponseEntity<List<Reserva>> findByPacienteId(@PathVariable("id") Integer pacienteId){
		try {
			List<Reserva> reservas= reservaService.findByPacienteId(pacienteId);
			return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Reserva>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Reserva", notes="Servicio para crear una nuevo Reserva")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Reserva creada correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Reserva> insertReserva(@Valid @RequestBody Reserva Reserva){
		try {
			Reserva ReservaNew = new Reserva();
			ReservaNew = reservaService.save(Reserva);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(ReservaNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Reserva", notes="Servicio para actualizar una reserva")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Reserva actualizada correctamente"),
			@ApiResponse(code=404, message="Reserva no encontrada")
	})
	public ResponseEntity<Reserva> updateReserva(@Valid @RequestBody Reserva Reserva){
		try {
			reservaService.save(Reserva);			
			return new ResponseEntity<Reserva>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Reserva", notes="Servicio para eliminar una Reserva")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Reserva eliminada correctamente"),
			@ApiResponse(code=404, message="Reserva no encontrada")
	})
	public ResponseEntity<Reserva> deleteReserva(@PathVariable("id") Integer id){
		try {
			Optional<Reserva> reserva= reservaService.findById(id);
			if(!reserva.isPresent()) {
				return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
			}
			reservaService.deleteById(id);
			return new ResponseEntity<Reserva>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//BUSQUEDA POR FECHA - java time.LocalDate
	@GetMapping(value="/searchByFecha/{fechaCita}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Reservas por fechas", notes="Servicio para buscar reservas por fecha")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Reservas encontradas"),
			@ApiResponse(code=404, message="Reservas no encontradas")
	})	
	public ResponseEntity<List<Reserva>> findByFecha(@PathVariable("fechaCita") LocalDate fechaCita){
		try {
			List<Reserva> reservas = new ArrayList<>(); 
			reservas = reservaService.findByFecha(fechaCita);
			return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<List<Reserva>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
