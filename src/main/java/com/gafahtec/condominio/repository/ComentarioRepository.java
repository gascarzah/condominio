package com.gafahtec.condominio.repository;

import com.gafahtec.condominio.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
