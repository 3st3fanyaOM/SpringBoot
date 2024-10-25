package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Cancion {

	private Integer id;
	private String titulo;
	List<Artista> artistas;

	public Cancion() {
		this.artistas = new ArrayList<>();
	}

	public Cancion(Integer id, String titulo, List<Artista> artistas) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.artistas = artistas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

}
