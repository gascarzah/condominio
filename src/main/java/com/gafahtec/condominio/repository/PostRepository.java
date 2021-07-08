package com.gafahtec.condominio.repository;

import com.gafahtec.condominio.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
}
