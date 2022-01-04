package com.gafahtec.condominio.repository;

import org.springframework.stereotype.Repository;

import com.gafahtec.condominio.model.Persona;


@Repository
public interface IPersonaRepository extends IGenericRepository<Persona,Integer>{
}
