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
import com.gafahtec.condominio.model.Torre;
import com.gafahtec.condominio.service.ITorreService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/torres")
@AllArgsConstructor
public class TorreController {

	private ITorreService iTorreService;
	
	@GetMapping
	public ResponseEntity<List<Torre>> listar() throws Exception{
		List<Torre> lista = iTorreService.listar();
		return new ResponseEntity<List<Torre>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Torre> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Torre obj = iTorreService.listarPorId(id);
		
		if(obj.getIdTorre() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Torre>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Torre> registrar(@Valid @RequestBody Torre p) throws Exception{
		Torre obj = iTorreService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTorre()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Torre> modificar(@Valid @RequestBody Torre p) throws Exception{
		Torre obj = iTorreService.modificar(p);
		return new ResponseEntity<Torre>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Torre obj = iTorreService.listarPorId(id);
		
		if(obj.getIdTorre() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iTorreService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
