package com.gafahtec.condominio.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.condominio.model.Torre;
import com.gafahtec.condominio.repository.IGenericRepository;
import com.gafahtec.condominio.repository.ITorreRepository;
import com.gafahtec.condominio.service.ITorreService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class TorreServiceImpl extends CRUDImpl<Torre, Integer>  implements ITorreService {

	
	private ITorreRepository repo;
	
	@Override
	protected IGenericRepository<Torre, Integer> getRepo() {
		
		return repo;
	}
}
