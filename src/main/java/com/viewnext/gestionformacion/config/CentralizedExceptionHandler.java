package com.viewnext.gestionformacion.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CentralizedExceptionHandler {
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
	
		String tipoRequerido = ex.getRequiredType().getSimpleName();
		String tipoEntrante = ex.getValue().getClass().getSimpleName();
		
		return ResponseEntity.badRequest().body("El valor [" + ex.getValue() + "] es de tipo [" + tipoEntrante+ "]. Se requiere un tipo [" + tipoRequerido + "]");
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex){
		
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){
		
		return ResponseEntity.internalServerError().body("Se ha producido un error en el servidor.");
	}

}
