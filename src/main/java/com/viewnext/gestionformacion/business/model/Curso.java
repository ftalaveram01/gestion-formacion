package com.viewnext.gestionformacion.business.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Curso {

	private Long id;
	private String nombre;
	private String descripcion;
	private List<Usuario> listaMiembros;
	private Date fechaInicio;
	private Date fechaFin;
	
}
