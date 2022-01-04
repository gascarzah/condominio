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
import com.gafahtec.condominio.model.Departamento;
import com.gafahtec.condominio.service.IDepartamentoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/departamentos")
@AllArgsConstructor
public class DepartamentoController {

	private IDepartamentoService iDepartamentoService;
	
	@GetMapping
	public ResponseEntity<List<Departamento>> listar() throws Exception{
		List<Departamento> lista = iDepartamentoService.listar();
		return new ResponseEntity<List<Departamento>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Departamento> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Departamento obj = iDepartamentoService.listarPorId(id);
		
		if(obj.getIdDepartamento() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Departamento>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Departamento> registrar(@Valid @RequestBody Departamento p) throws Exception{
		Departamento obj = iDepartamentoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdDepartamento()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Departamento> modificar(@Valid @RequestBody Departamento p) throws Exception{
		Departamento obj = iDepartamentoService.modificar(p);
		return new ResponseEntity<Departamento>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Departamento obj = iDepartamentoService.listarPorId(id);
		
		if(obj.getIdDepartamento() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iDepartamentoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
