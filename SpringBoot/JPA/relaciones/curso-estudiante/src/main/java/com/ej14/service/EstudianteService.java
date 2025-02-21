package com.ej14.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ej14.model.Estudiante;

@Service
public interface EstudianteService {

	List<Estudiante> findEstudiantesByNombre(String nombre);

	Estudiante updateEmail(Integer id, String email);

	Estudiante findEstudianteById(Integer id);

	List<Estudiante> findAllEstudiantes();

}
