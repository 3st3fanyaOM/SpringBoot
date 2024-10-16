package com.example.demo.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pelicula {

	private Integer id;
	private String titulo;
	private String director;
	private LocalDate lanzamiento;
	private Integer duracion;
	private List<Actor> actores;

	public Pelicula() {
		this.actores = new ArrayList<>();
	}

	public Pelicula(Integer id, String titulo, String director, LocalDate lanzamiento, Integer duracion,
			List<Actor> actores) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.director = director;
		this.lanzamiento = lanzamiento;
		this.duracion = duracion;
		this.actores = actores;
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public LocalDate getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(LocalDate lanzamiento) {
		this.lanzamiento = lanzamiento;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public List<Actor> getActores() {
		return actores;
	}

	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}

}
