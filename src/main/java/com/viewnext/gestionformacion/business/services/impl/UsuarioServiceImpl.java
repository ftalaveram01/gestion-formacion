package com.viewnext.gestionformacion.business.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.viewnext.gestionformacion.business.model.Usuario;
import com.viewnext.gestionformacion.business.services.UsuarioService;
import com.viewnext.gestionformacion.integration.repository.UsuarioRepository;

/**
 * Implementa los servicios a traves del repository
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	/**
	 * Metodo para logear al usuario atraves de su email y password
	 * 
	 * Puede que no encuentre al usuario, en tal caso retorna null.
	 */
	@Override
	public Optional<Usuario> login(String email, String password) {
		return Optional.ofNullable(usuarioRepository.findByEmailAndPassword(email, password));
	}

	/**
	 * Metodo para registrar al usuario a traves de su email y password
	 * 
	 * Si no existe un usuario con ese email registrado
	 * Crea un nuevo usuario, lo guarda en el repositorio y devuelve el id del usuario guardado
	 * 
	 * Si existe, devuelve una excepcion
	 */
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
