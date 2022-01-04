package com.gafahtec.condominio.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.condominio.model.Vehiculo;
import com.gafahtec.condominio.repository.IGenericRepository;
import com.gafahtec.condominio.repository.IVehiculoRepository;
import com.gafahtec.condominio.service.IVehiculoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class VehiculoServiceImpl extends CRUDImpl<Vehiculo, Integer>  implements IVehiculoService {

	
	private IVehiculoRepository repo;
	
	@Override
	protected IGenericRepository<Vehiculo, Integer> getRepo() {
		
		return repo;
	}
}
