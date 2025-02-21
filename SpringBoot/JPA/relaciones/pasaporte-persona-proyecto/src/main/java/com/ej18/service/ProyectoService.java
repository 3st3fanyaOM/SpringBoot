package com.ej18.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ej18.model.Pasaporte;
import com.ej18.model.Persona;
import com.ej18.model.Proyecto;


@Service
public interface ProyectoService {

	void guardarPersona(Persona persona);

	void guardarProyecto(Proyecto proyecto);
	
	void guardarPasaporte(Pasaporte p);
	
	void asignarPasaporteAPersona(Persona persona, Pasaporte pasaporte);
	
	void asignarProyectoAPersona(Persona persona, Proyecto proyecto);

	List<Persona> obtenerTodasLasPersonas();
	
	void eliminarProyectoDePersona(Persona persona, Proyecto proyecto);

	Persona obtenerPersonaPorId(Long id);


	
}
