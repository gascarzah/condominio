package com.gafahtec.condominio.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.condominio.model.Persona;
import com.gafahtec.condominio.repository.IGenericRepository;
import com.gafahtec.condominio.repository.IPersonaRepository;
import com.gafahtec.condominio.service.IPersonaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class PersonaServiceImpl extends CRUDImpl<Persona, Integer>  implements IPersonaService {

	
	private IPersonaRepository repo;
	
	@Override
	protected IGenericRepository<Persona, Integer> getRepo() {
		
		return repo;
	}
}
