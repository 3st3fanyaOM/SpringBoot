package com.ej14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej14.model.Estudiante;
import com.ej14.repository.EstudianteRepository;

import jakarta.transaction.Transactional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
	
	    @Autowired
	    private EstudianteRepository estudianteRepository;

	    // Consultar todos los estudiantes
	    @Override
	    public List<Estudiante> findAllEstudiantes() {
	        return estudianteRepository.findAll();
	    }

	    // Consultar un estudiante por ID
	    @Override
	    public Estudiante findEstudianteById(Integer id) {
	        return estudianteRepository.findById(id);
	    }

	    // Actualizar email de un estudiante
	    @Transactional
	    @Override
	    public Estudiante updateEmail(Integer id, String email) {
	        Estudiante estudiante = findEstudianteById(id);
	        if (estudiante != null) {
	            estudiante.setEmail(email);
	            return estudianteRepository.save(estudiante);
	        }
	        return null;
	    }

	    // Buscar estudiantes por nombre
	    @Override
	    public List<Estudiante> findEstudiantesByNombre(String nombre) {
	        return estudianteRepository.findByNombreContaining(nombre);
	    }
	
}
