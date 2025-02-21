package com.ej18.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej18.model.Pasaporte;
import com.ej18.model.Persona;
import com.ej18.model.Proyecto;
import com.ej18.repository.ProyectoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProyectoServiceImpl implements ProyectoService {
	@Autowired
	private ProyectoRepository repositorio;

	@Override
	@Transactional
	public void guardarPersona(Persona persona) {
		repositorio.guardarPersona(persona);
	}

	@Override
	@Transactional
	public void guardarPasaporte(Pasaporte p) {
		repositorio.guardarPasaporte(p);
	}

	// Guardar o actualizar un proyecto
	@Override
	@Transactional
	public void guardarProyecto(Proyecto proyecto) {
		repositorio.guardarProyecto(proyecto);
	}

	@Override
	@Transactional
	public void asignarPasaporteAPersona(Persona persona, Pasaporte pasaporte) {

		repositorio.asignarPasaporteAPersona(persona,pasaporte);
	}

	@Override
	@Transactional
	public void asignarProyectoAPersona(Persona persona, Proyecto proyecto) {
		
		repositorio.asignarProyectoAPersona(persona,proyecto);
	}

	@Override
	public List<Persona> obtenerTodasLasPersonas() {
		return repositorio.obtenerTodasLasPersonas();
	}

	@Override
	@Transactional
	public void eliminarProyectoDePersona(Persona persona, Proyecto proyecto) {

		repositorio.eliminarProyectoDePersona(persona,proyecto);
		
	}
	
	@Override
	public Persona obtenerPersonaPorId(Long id) {
		return repositorio.buscarPersonaPorId(id);
	}

	
	
}
