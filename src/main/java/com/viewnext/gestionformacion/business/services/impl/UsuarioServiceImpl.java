package com.viewnext.gestionformacion.business.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.viewnext.gestionformacion.business.model.Usuario;
import com.viewnext.gestionformacion.business.services.UsuarioService;
import com.viewnext.gestionformacion.integration.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Optional<Usuario> login(String email, String password) {
		return Optional.ofNullable(usuarioRepository.findByEmailAndPassword(email, password));
	}

	@Override
	public Long register(String email, String password) {
		
		if(usuarioRepository.existsByEmail(email))
			throw new IllegalStateException("Existe un usuario con ese email.");
		
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setPassword(password);
		
		Usuario guardado = usuarioRepository.save(usuario);
		
		return guardado.getId();
	}

}
