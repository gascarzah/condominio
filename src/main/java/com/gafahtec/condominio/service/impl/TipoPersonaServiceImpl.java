package com.gafahtec.condominio.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.condominio.model.TipoPersona;
import com.gafahtec.condominio.repository.IGenericRepository;
import com.gafahtec.condominio.repository.ITipoPersonaRepository;
import com.gafahtec.condominio.service.ITipoPersonaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class TipoPersonaServiceImpl extends CRUDImpl<TipoPersona, Integer>  implements ITipoPersonaService {

	
	private ITipoPersonaRepository repo;
	
	@Override
	protected IGenericRepository<TipoPersona, Integer> getRepo() {
		
		return repo;
	}
}
