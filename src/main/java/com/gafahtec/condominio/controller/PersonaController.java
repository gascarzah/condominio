package com.gafahtec.condominio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.gafahtec.condominio.exception.ModeloNotFoundException;
import com.gafahtec.condominio.model.Persona;
import com.gafahtec.condominio.service.IPersonaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/personas")
@AllArgsConstructor
public class PersonaController {

	private IPersonaService iPersonaService;
	
	@GetMapping
	public ResponseEntity<List<Persona>> listar() throws Exception{
		List<Persona> lista = iPersonaService.listar();
		return new ResponseEntity<List<Persona>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Persona obj = iPersonaService.listarPorId(id);
		
		if(obj.getIdPersona() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Persona>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Persona> registrar(@Valid @RequestBody Persona p) throws Exception{
		Persona obj = iPersonaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPersona()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Persona> modificar(@Valid @RequestBody Persona p) throws Exception{
		Persona obj = iPersonaService.modificar(p);
		return new ResponseEntity<Persona>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Persona obj = iPersonaService.listarPorId(id);
		
		if(obj.getIdPersona() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iPersonaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
