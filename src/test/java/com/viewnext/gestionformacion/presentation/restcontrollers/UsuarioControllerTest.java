package com.viewnext.gestionformacion.presentation.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MvcResult;

import com.viewnext.gestionformacion.business.model.Usuario;
import com.viewnext.gestionformacion.business.services.UsuarioService;
import com.viewnext.gestionformacion.presentation.restcontroller.UsuarioController;


@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest extends AbstractControllerTest{

    @MockitoBean
    private UsuarioService usuarioService;

    private Usuario usuario;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup(){
        initObjects();
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testRegistrar() throws Exception {

        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setId(null);
        usuarioNuevo.setEmail("nuevoUsuario@email.com");
        usuarioNuevo.setPassword("1234");

        when(usuarioService.register(usuarioNuevo.getEmail(), usuarioNuevo.getPassword())).thenReturn(500L);

        MvcResult result = mockMvc.perform(post("/usuarios/registrar")
        .param("email", usuarioNuevo.getEmail())
        .param("password", usuarioNuevo.getPassword()))
        .andReturn();

        assertEquals(201, result.getResponse().getStatus());
    }

    private void initObjects(){
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("prueba@email.com");
        usuario.setPassword("1234");
    }
}
