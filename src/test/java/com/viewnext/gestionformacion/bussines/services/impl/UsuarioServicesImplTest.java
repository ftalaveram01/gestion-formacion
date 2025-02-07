package com.viewnext.gestionformacion.bussines.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.viewnext.gestionformacion.business.model.Usuario;
import com.viewnext.gestionformacion.business.services.impl.UsuarioServiceImpl;
import com.viewnext.gestionformacion.integration.repository.UsuarioRepository;

public class UsuarioServicesImplTest {

@Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup(){
        initObjects();
        MockitoAnnotations.initMocks(this);
        usuarioServiceImpl= new UsuarioServiceImpl(usuarioRepository);
    }

    private Usuario usuario;

    @Test
    public void testRegisterNuevoUsuario(){


        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Long idRegistro = usuarioServiceImpl.register(usuario.getEmail(), usuario.getPassword());

        assertNotNull(idRegistro);
        assertEquals(usuario.getId(), idRegistro);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));

    }

    @Test
    public void testRegisterUsuarioExistente(){

        when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(true);

       assertThrows(IllegalStateException.class,() ->  usuarioServiceImpl.register(usuario.getEmail(), usuario.getPassword()));
    }

    @Test
    public void testRegisterEmailNull(){

        usuario.setEmail(null);

       assertThrows(NullPointerException.class,() ->  usuarioServiceImpl.register(usuario.getEmail(), usuario.getPassword()));
    }

    @Test
    public void testRegisterPasswordNull(){

        usuario.setPassword(null);

       assertThrows(NullPointerException.class,() ->  usuarioServiceImpl.register(usuario.getEmail(), usuario.getPassword()));
    }

    private void initObjects(){
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("prueba@email.com");
        usuario.setPassword("1234");
    }

}
