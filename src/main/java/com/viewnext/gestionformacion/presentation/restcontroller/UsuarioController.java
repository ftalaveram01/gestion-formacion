package com.viewnext.gestionformacion.presentation.restcontroller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.viewnext.gestionformacion.busines.services.UsuarioService;
import com.viewnext.gestionformacion.business.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam(required = true) String email, @RequestParam(required = true) String password){
		
		Optional<Usuario> usuario = usuarioService.login(email, password);
		
		if(usuario.isEmpty())
			throw new IllegalStateException("Login incorrecto.");
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestParam(required = true) String email, @RequestParam(required = true) String password, UriComponentsBuilder ucb){
		
		Long id = usuarioService.register(email, password);
		
		return ResponseEntity.created(ucb.path("/usuarios/{id}").build(id)).build();
	}

}
