package com.viewnext.gestionformacion.services.impl;

import java.util.Optional;

import com.viewnext.gestionformacion.business.model.Usuario;
import com.viewnext.gestionformacion.repository.UsuarioRepository;
import com.viewnext.gestionformacion.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Optional<Usuario> login(String email, String password) {
		
		return usuarioRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public Long register(String email, String password) {
		
		if(usuarioRepository.existsByEmail(email))
			throw new IllegalStateException("Existe un usuario con ese email.");
		
		Usuario usu1 = new Usuario();
		usu1.setEmail(email);
		usu1.setPassword(password);
		
		Usuario guardado = usuarioRepository.save(usu1);
		
		return guardado.getId();
	}

}
