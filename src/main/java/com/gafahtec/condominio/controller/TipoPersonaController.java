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
import com.gafahtec.condominio.model.TipoPersona;
import com.gafahtec.condominio.service.ITipoPersonaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/tipoPersonas")
@AllArgsConstructor
public class TipoPersonaController {

	private ITipoPersonaService iTipoPersonaService;
	
	@GetMapping
	public ResponseEntity<List<TipoPersona>> listar() throws Exception{
		List<TipoPersona> lista = iTipoPersonaService.listar();
		return new ResponseEntity<List<TipoPersona>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoPersona> listarPorId(@PathVariable("id") Integer id) throws Exception{
		TipoPersona obj = iTipoPersonaService.listarPorId(id);
		
		if(obj.getIdTipoPersona() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<TipoPersona>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TipoPersona> registrar(@Valid @RequestBody TipoPersona p) throws Exception{
		TipoPersona obj = iTipoPersonaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoPersona()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<TipoPersona> modificar(@Valid @RequestBody TipoPersona p) throws Exception{
		TipoPersona obj = iTipoPersonaService.modificar(p);
		return new ResponseEntity<TipoPersona>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		TipoPersona obj = iTipoPersonaService.listarPorId(id);
		
		if(obj.getIdTipoPersona() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iTipoPersonaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
