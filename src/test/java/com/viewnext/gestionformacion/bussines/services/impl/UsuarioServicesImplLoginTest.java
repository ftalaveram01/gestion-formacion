package com.viewnext.gestionformacion.bussines.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.viewnext.gestionformacion.business.model.Usuario;
import com.viewnext.gestionformacion.business.services.impl.UsuarioServiceImpl;
import com.viewnext.gestionformacion.integration.repository.UsuarioRepository;

public class UsuarioServicesImplLoginTest {
	
	@Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    private Usuario user;
    
    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup(){
    	initObject();
        MockitoAnnotations.initMocks(this);
        usuarioServiceImpl= new UsuarioServiceImpl(usuarioRepository);
    }
    
    @Test
    public void loginTest() {
    	
    	 when(usuarioRepository.findByEmailAndPassword("prueba@gmail.com", "1234")).thenReturn(user);
         when(usuarioRepository.findByEmailAndPassword("error@gmail.com", "12345")).thenReturn(null);

         Optional<Usuario> optional1 = usuarioServiceImpl.login("prueba@gmail.com", "1234");
         Optional<Usuario> optional2 = usuarioServiceImpl.login("error@gmail.com", "12345");

         assertTrue(optional1.isPresent());
         assertTrue(optional2.isEmpty());

         assertEquals(1L, optional1.get().getId());
    	
    }
    
    //************************************
    //
    // Private method
    //
    //************************************

    private void initObject() {
        user = new Usuario();
        user.setId(1L);
        user.setEmail("prueba@email.com");
        user.setPassword("1234");
    }
    
}
