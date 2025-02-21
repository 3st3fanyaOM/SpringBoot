package com.ej14.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej14.model.Curso;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Crear o actualizar un curso
    @Override
    public Curso save(Curso curso) {
        if (curso.getId() == null) {
            entityManager.persist(curso); // Si el ID es nulo, es un nuevo curso, lo insertamos
        } else {
            entityManager.merge(curso); // Si ya existe, lo actualizamos
        }
        return curso;
    }

    // Buscar todos los cursos
    @Override
    public List<Curso> findAll() {
        return entityManager.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    // Buscar un curso por ID
    @Override
    public Curso findById(Integer id) {
        return entityManager.find(Curso.class, id);
    }

    @Override
    public void delete(Curso curso) {
        entityManager.remove(curso);
    }

    // Eliminar un curso por ID
    @Override
    public void deleteById(Integer id) {
        Curso curso = findById(id);
        if (curso != null) {
            delete(curso);
        }
    }

    // Buscar cursos por nombre (usando LIKE para encontrar coincidencias parciales)
    @Override
    public List<Curso> findByNombreContaining(String nombre) {
        return entityManager.createQuery("SELECT c FROM Curso c WHERE c.nombre LIKE :nombre", Curso.class)
                            .setParameter("nombre", "%" + nombre + "%")
                            .getResultList();
    }

	
	
}
