package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Artista {

	private Integer id;
	private String nombre;
	private String nacionalidad;
	List<Cancion> canciones;

	public Artista() {
		this.canciones = new ArrayList<>();
	}

	public Artista(Integer id, String nombre, String nacionalidad, List<Cancion> canciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.canciones = canciones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

}
