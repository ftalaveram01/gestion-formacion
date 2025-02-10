package com.viewnext.gestionformacion.presentation.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

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
public class UsuarioControllerLoginTest extends AbstractControllerTest{

    @MockitoBean
    private UsuarioService usuarioService;

    private Usuario user;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup(){
        initObject();
        MockitoAnnotations.initMocks(this);
    }

	 @Test
	 public void testLoginFalse() throws Exception {
		 when(usuarioService.login("error@gmail.com", "123456")).thenReturn(Optional.empty());
		 
	     mockMvc.perform(get("/usuarios/login")
	            .param("email", "error@email.com")
	            .param("password", "wrongpassword"))
	            .andExpect(status().isBadRequest());
	     
	 }

	    
	 //************************************
	 //
	 // Private method
	 //
	 //************************************

	 private void initObject() {
		 user = new Usuario();
		 user.setId(1L);
	     user.setEmail("prueba@gmail.com");
	     user.setPassword("1234");
	 }
	
}
