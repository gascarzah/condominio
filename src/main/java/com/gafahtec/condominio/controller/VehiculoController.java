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
import com.gafahtec.condominio.model.Vehiculo;
import com.gafahtec.condominio.service.IVehiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/vehiculos")
@AllArgsConstructor
public class VehiculoController {

	private IVehiculoService iVehiculoService;
	
	@GetMapping
	public ResponseEntity<List<Vehiculo>> listar() throws Exception{
		List<Vehiculo> lista = iVehiculoService.listar();
		return new ResponseEntity<List<Vehiculo>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Vehiculo> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Vehiculo obj = iVehiculoService.listarPorId(id);
		
		if(obj.getIdVehiculo() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Vehiculo>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Vehiculo> registrar(@Valid @RequestBody Vehiculo p) throws Exception{
		Vehiculo obj = iVehiculoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVehiculo()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Vehiculo> modificar(@Valid @RequestBody Vehiculo p) throws Exception{
		Vehiculo obj = iVehiculoService.modificar(p);
		return new ResponseEntity<Vehiculo>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Vehiculo obj = iVehiculoService.listarPorId(id);
		
		if(obj.getIdVehiculo() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iVehiculoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
