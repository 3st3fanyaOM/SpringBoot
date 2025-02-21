package com.ej14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej14.model.Curso;
import com.ej14.model.Estudiante;
import com.ej14.repository.CursoRepository;
import com.ej14.repository.EstudianteRepository;

import jakarta.transaction.Transactional;

@Service
public class CursoServiceImpl implements CursoService{
	@Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Crear un curso con estudiantes
    @Transactional
    @Override
    public void createCurso(Curso curso) {
        
        cursoRepository.save(curso);
    }

    // Consultar todos los cursos
    @Override
    public List<Curso> findAllCursos() {
        return cursoRepository.findAll();
    }

    // Consultar un curso por ID
    @Override
    public Curso findCursoById(Integer id) {
        return cursoRepository.findById(id);
    }

    // Actualizar curso (nombre y descripción)
    @Transactional
    @Override
    public Curso updateCurso(Integer id, String nombre, String descripcion) {
        Curso curso = findCursoById(id);
        if (curso != null) {
            curso.setNombre(nombre);
            curso.setDescripcion(descripcion);
            return cursoRepository.save(curso);
        }
        return null;
    }

    // Eliminar un curso por ID
    @Transactional
    @Override
    public void deleteCurso(Integer id) {
        Curso curso = findCursoById(id);
        if (curso != null) {
            cursoRepository.delete(curso);
        }
    }

    // Agregar un estudiante a un curso
    @Transactional
    @Override
    public void addEstudianteToCurso(Integer cursoId, Estudiante estudiante) {
        Curso curso = findCursoById(cursoId);
        if (curso != null) {
            estudiante.setCurso(curso);
        	curso.addEstudiante(estudiante);
            
            cursoRepository.save(curso);
        }
    }

    // Eliminar un estudiante de un curso
    @Transactional
    @Override
    public void removeEstudianteFromCurso(Integer cursoId, Integer estudianteId) {
        Curso curso = findCursoById(cursoId);
        if (curso != null) {
            Estudiante estudiante = estudianteRepository.findById(estudianteId);
            if (estudiante != null) {
                curso.removeEstudiante(estudiante);
                estudiante.setCurso(null);
                cursoRepository.save(curso);                
            }
        }
    }

    // Obtener los estudiantes de un curso
    @Override
    public List<Estudiante> getEstudiantesByCursoId(Integer cursoId) {
        Curso curso = findCursoById(cursoId);
        return (curso != null) ? curso.getEstudiantes() : null;
    }

	
	
	 @Override
	 public List<Curso> findByNombreContaining(String nombre) {
		 return cursoRepository.findByNombreContaining(nombre);
		 
	 }
	
}
