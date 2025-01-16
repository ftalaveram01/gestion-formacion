package com.viewnext.gestionformacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viewnext.gestionformacion.business.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByEmailAndPassword(String email, String password);
	
	Boolean existsByEmail(String email);
}
