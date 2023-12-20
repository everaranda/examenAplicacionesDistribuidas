package com.aranda.denunciaservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DenunciaDTO {
	private int id;
	private String nombre;
	private Double direccion;
	private String dni;
	private String fecha;
	private String titulo;
	private String descripcion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getdireccion() {
		return direccion;
	}
	public void setdireccion(Double direccion) {
		this.direccion = direccion;
	}
	
	public String getdni() {
		return dni;
	}
	public void setdni(String dni) {
		this.dni = dni;
	}
	public String getfecha() {
		return fecha;
	}
	public void setfecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String gettitulo() {
		return titulo;
	}
	public void settitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getdescripcion() {
		return descripcion;
	}
	public void setdescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
