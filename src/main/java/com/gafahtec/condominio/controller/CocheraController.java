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
import com.gafahtec.condominio.model.Cochera;
import com.gafahtec.condominio.service.ICocheraService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/cocheras")
@AllArgsConstructor
public class CocheraController {

	private ICocheraService iCocheraService;
	
	@GetMapping
	public ResponseEntity<List<Cochera>> listar() throws Exception{
		List<Cochera> lista = iCocheraService.listar();
		return new ResponseEntity<List<Cochera>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cochera> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Cochera obj = iCocheraService.listarPorId(id);
		
		if(obj.getIdCochera() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Cochera>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cochera> registrar(@Valid @RequestBody Cochera p) throws Exception{
		Cochera obj = iCocheraService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCochera()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Cochera> modificar(@Valid @RequestBody Cochera p) throws Exception{
		Cochera obj = iCocheraService.modificar(p);
		return new ResponseEntity<Cochera>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Cochera obj = iCocheraService.listarPorId(id);
		
		if(obj.getIdCochera() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iCocheraService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
