package com.gafahtec.condominio.repository;

import com.gafahtec.condominio.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
