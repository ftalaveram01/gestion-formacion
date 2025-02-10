package com.viewnext.gestionformacion.presentation.restcontroller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.viewnext.gestionformacion.business.model.Usuario;
import com.viewnext.gestionformacion.business.services.UsuarioService;

/**
 * Controlador REST para gestionar los usuarios
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	/**
	 * 
     * Este metodo permite a un usuario iniciar sesion por su email y contraseña
     * Si son incorrectos, lanza una exception
     *
     * @param email
     * @param password
     * @return una ResponseEntity que contiene la información del usuario si el inicio de sesión es exitoso
     */
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam(required = true) String email, @RequestParam(required = true) String password){
		
		Optional<Usuario> usuario = usuarioService.login(email, password);
		
		if(usuario.isEmpty())
			throw new IllegalStateException("Login incorrecto.");
		
		return ResponseEntity.ok(usuario);
	}
	
	/**
     * 
     * Este metodo permite registrar un nuevo usuario por su email y contraseña
     * Devuelve una ResponseEntity con la URI del nuevo usuario registrado
     *
     * @param email
     * @param password
     * @param ucb un UriComponentsBuilder para construir la URI del recurso creado
     * @return una ResponseEntity con la URI del nuevo usuario registrado
     */
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestParam(required = true) String email, @RequestParam(required = true) String password, UriComponentsBuilder ucb){
		
		Long id = usuarioService.register(email, password);
		
		return ResponseEntity.created(ucb.path("/usuarios/{id}").build(id)).build();
	}

}
