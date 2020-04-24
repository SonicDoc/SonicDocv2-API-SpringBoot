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

import com.sonicdoc.entities.Usuario;
import com.sonicdoc.service.IUsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/usuarios")
@Api(tags="Usuarios",value="Servicio Api de Usuarios")
public class UsuariosController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Listar Usuarios", notes="Servicio para listar todos los usuarios")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Usuarios encontrados"),
			@ApiResponse(code=404, message="Usuarios no encontrados")
	})	
	public ResponseEntity<List<Usuario>> findAll(){
		try {
			List<Usuario> usuarios = new ArrayList<>();
			usuarios = usuarioService.findAll();
			return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<List<Usuario>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Usuario por Id", notes="Servicio para buscar un usuario por Id")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Usuario encontrado"),
			@ApiResponse(code=404, message="Usuario no encontrado")
	})
	public ResponseEntity<Usuario> findById(@PathVariable("id") Integer id){
		try {
			
			Optional<Usuario> usuario= usuarioService.findById(id);
			if(!usuario.isPresent()) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/searchByDni/{dni}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Buscar Usuario por Dni", notes="Servicio para buscar un Usuario por Dni")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Usuario encontrado"),
			@ApiResponse(code=404, message="Usuario no encontrado")
	})
	public ResponseEntity<Usuario> findByDni(@PathVariable("dni") String dni){
		try {
			Usuario usuario= usuarioService.findByDni(dni);
			if(usuario==null) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Crear Usuario", notes="Servicio para crear un nuevo usuario")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Usuario creado correctamente"),
			@ApiResponse(code=400, message="Solicitud inválida")
	})
	public ResponseEntity<Usuario> insertUsuario(@Valid @RequestBody Usuario usuario){
		try {
			Usuario usuarioNew = new Usuario();
			usuarioNew = usuarioService.save(usuario);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(usuarioNew.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	@ApiOperation(value="Actualizar Usuario", notes="Servicio para actualizar un usuario")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Usuario actualizado correctamente"),
			@ApiResponse(code=404, message="Usuario no encontrado")
	})
	public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario){
		try {
			usuarioService.save(usuario);			
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Eliminar Usuario", notes="Servicio para eliminar un usuario")
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Usuario eliminado correctamente"),
			@ApiResponse(code=404, message="Usuario no encontrado")
	})
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") Integer id){
		try {
			Optional<Usuario> usuario= usuarioService.findById(id);
			if(!usuario.isPresent()) {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
			usuarioService.deleteById(id);
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
