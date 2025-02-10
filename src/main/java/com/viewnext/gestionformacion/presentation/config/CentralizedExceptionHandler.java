package com.viewnext.gestionformacion.presentation.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Esta clase maneja las excepciones que pueden aparacer en nuestra aplicación
 */
@ControllerAdvice
public class CentralizedExceptionHandler {
	
	/**
	 * Maneja MethodArgumentTypeMismatchException
	 *
	 * Este método se activa cuando se pasa un argumento de un tipo incorrecto a un método
	 * Extrae el tipo requerido y el tipo real del argumento, y luego devuelve una
	 * entidad de respuesta con un mensaje de error explicado
	 *
	 * @param ex la excepción lanzada cuando un argumento de un método no es del tipo esperado
	 * @return un ResponseEntity que contiene un estado de solicitud incorrecta 
	 * 		   y un mensaje de error que especifica los tipos de argumento esperado
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
	
		String tipoRequerido = ex.getRequiredType().getSimpleName();
		String tipoEntrante = ex.getValue().getClass().getSimpleName();
		
		return ResponseEntity.badRequest().body("El valor [" + ex.getValue() + "] es de tipo [" + tipoEntrante+ "]. Se requiere un tipo [" + tipoRequerido + "]");
	}
	
	
	/**
	 * Maneja IllegalStateException
	 *
	 * Este método se activa cuando se lanza una IllegalStateException
	 * Devuelve una entidad de respuesta con un estado de solicitud incorrecta y el mensaje de la excepción
	 *
	 * @param ex la IllegalStateException lanzada
	 * @return un ResponseEntity que contiene un estado de solicitud incorrecta y el mensaje de la excepción
	 */
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex){
		
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	/**
	 * Maneja excepciones genéricas
	 *
	 * Este método se activa cuando se lanza una excepción no especificada
	 * Devuelve una entidad de respuesta con un estado de error interno del servidor y un mensaje de error general
	 *
	 * @param ex la excepción lanzada
	 * @return un ResponseEntity que contiene un estado de error interno del servidor y un mensaje de error
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){
		
		return ResponseEntity.internalServerError().body("Se ha producido un error en el servidor.");
	}

}
