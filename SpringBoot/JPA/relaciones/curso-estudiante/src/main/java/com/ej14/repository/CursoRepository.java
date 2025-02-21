package com.ej14.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej14.model.Curso;
import com.ej14.model.Estudiante;

import jakarta.transaction.Transactional;

@Repository
public interface CursoRepository {

	List<Curso> findByNombreContaining(String nombre);

	void deleteById(Integer id);

	Curso save(Curso curso);

	List<Curso> findAll();

	Curso findById(Integer id);

	void delete(Curso curso);

}
