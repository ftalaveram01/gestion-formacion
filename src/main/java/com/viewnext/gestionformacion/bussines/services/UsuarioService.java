package com.viewnext.gestionformacion.bussines.services;

import java.util.Optional;

import com.viewnext.gestionformacion.business.model.Usuario;

public interface UsuarioService {
	
	Optional<Usuario> login(String email, String password);
	
	Long register(String email, String password);
	
}
