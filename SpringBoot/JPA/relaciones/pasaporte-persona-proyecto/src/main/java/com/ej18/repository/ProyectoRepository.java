package com.ej18.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej18.model.Pasaporte;
import com.ej18.model.Persona;
import com.ej18.model.Proyecto;

@Repository
public interface ProyectoRepository {

	void guardarPersona(Persona persona);
	void guardarProyecto(Proyecto proyecto);
	void guardarPasaporte(Pasaporte pasaporte);	
	Persona buscarPersonaPorId(Long id);
	Pasaporte buscarPasaportePorId(Long id);
	void actualizarPersona(Persona persona);
	
	List<Persona> obtenerTodasLasPersonas();
	Proyecto buscarProyectoPorId(Long id);
	void actualizarPasaporte(Pasaporte pas);
	void asignarProyectoAPersona(Persona persona, Proyecto proyecto);
	void asignarPasaporteAPersona(Persona persona, Pasaporte pasaporte);
	void eliminarProyectoDePersona(Persona persona, Proyecto proyecto);
	
	
	
}
