package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Album {

	private Integer id;
	private String titulo;
	private Artista artistaPrincipal;
	private Integer anioLanzamiento;
	private List<Cancion> canciones;

	public Album() {// crear asi
		this.canciones = new ArrayList<>();
	}

	public Album(Integer id, String titulo, Artista artistaPrincipal, Integer anioLanzamiento,
			List<Cancion> canciones) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.artistaPrincipal = artistaPrincipal;
		this.anioLanzamiento = anioLanzamiento;
		this.canciones = canciones;
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

	public Artista getArtistaPrincipal() {
		return artistaPrincipal;
	}

	public void setArtistaPrincipal(Artista artistaPrincipal) {
		this.artistaPrincipal = artistaPrincipal;
	}

	public Integer getAnioLanzamiento() {
		return anioLanzamiento;
	}

	public void setAnioLanzamiento(Integer anioLanzamiento) {
		this.anioLanzamiento = anioLanzamiento;
	}

	public List<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Cancion> canciones) {
		this.canciones = canciones;
	}

}
