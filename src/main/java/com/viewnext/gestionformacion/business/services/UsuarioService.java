package com.viewnext.gestionformacion.business.services;

import java.util.Optional;

import com.viewnext.gestionformacion.business.model.Usuario;

/**
 * Establece principales metodos de los usuarios
 */
public interface UsuarioService {
	
	/**
	 * Metodo para logear al usuario
	 * 
	 * @param email
	 * @param password
	 * @return devuelve un optional de usuario, ya que puede que el usuario sea null o no exista.
	 */
	Optional<Usuario> login(String email, String password);
	
	/**
	 * Metodo para registrar al usuario
	 * 
	 * @param email
	 * @param password
	 * @return devuelve el id del usuario registrado
	 */
	Long register(String email, String password);
	
}
