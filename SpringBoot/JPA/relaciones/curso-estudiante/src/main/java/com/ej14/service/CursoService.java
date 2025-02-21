package com.ej14.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ej14.model.Curso;
import com.ej14.model.Estudiante;


@Service
public interface CursoService {

	
	List<Curso> findAllCursos();

	Curso findCursoById(Integer id);

	Curso updateCurso(Integer id, String nombre, String descripcion);

	void deleteCurso(Integer id);

	void addEstudianteToCurso(Integer cursoId, Estudiante estudiante);

	void removeEstudianteFromCurso(Integer cursoId, Integer estudianteId);

	List<Estudiante> getEstudiantesByCursoId(Integer cursoId);

	void createCurso(Curso curso);
	
	List<Curso> findByNombreContaining(String nombre);

}
