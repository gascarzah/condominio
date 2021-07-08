package com.gafahtec.condominio.repository;

import com.gafahtec.condominio.model.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long>{
}
