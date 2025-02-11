package com.viewnext.gestionformacion.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viewnext.gestionformacion.business.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
}
