package com.gafahtec.condominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gafahtec.condominio.model.Cochera;

@Repository
public interface CocheraRepository extends JpaRepository<Cochera, Long>{
}
