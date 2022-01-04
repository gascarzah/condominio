package com.gafahtec.condominio.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.condominio.model.Cochera;
import com.gafahtec.condominio.repository.ICocheraRepository;
import com.gafahtec.condominio.repository.IGenericRepository;
import com.gafahtec.condominio.service.ICocheraService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class CocheraServiceImpl extends CRUDImpl<Cochera, Integer>  implements ICocheraService {

	
	private ICocheraRepository repo;
	
	@Override
	protected IGenericRepository<Cochera, Integer> getRepo() {
		
		return repo;
	}
}
