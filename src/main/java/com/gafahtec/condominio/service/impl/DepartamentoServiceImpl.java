package com.gafahtec.condominio.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.condominio.model.Departamento;
import com.gafahtec.condominio.repository.IDepartamentoRepository;
import com.gafahtec.condominio.repository.IGenericRepository;
import com.gafahtec.condominio.service.IDepartamentoService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class DepartamentoServiceImpl extends CRUDImpl<Departamento, Integer>  implements IDepartamentoService {

	
	private IDepartamentoRepository repo;
	
	@Override
	protected IGenericRepository<Departamento, Integer> getRepo() {
		
		return repo;
	}
}
