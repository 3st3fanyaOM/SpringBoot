package com.ej14.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ej14.model.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EstudianteRepositoryImpl implements EstudianteRepository {

	
	@PersistenceContext
    private EntityManager entityManager;

    // Crear o actualizar un estudiante
    public Estudiante save(Estudiante estudiante) {
        if (estudiante.getId() == null) {
            entityManager.persist(estudiante); // Si el ID es nulo, es un nuevo estudiante
        } else {
            entityManager.merge(estudiante); // Si ya existe, lo actualizamos
        }
        return estudiante;
    }

    // Buscar todos los estudiantes
    public List<Estudiante> findAll() {
        return entityManager.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
    }

    // Buscar un estudiante por ID
    public Estudiante findById(Integer id) {
    	System.out.println("el is :es " + id);
        return entityManager.find(Estudiante.class, id);
    }

    // Eliminar un estudiante por ID
    public void delete(Estudiante estudiante) {
        entityManager.remove(estudiante);
    }

    // Eliminar un estudiante por ID
    public void deleteById(Integer id) {
        Estudiante estudiante = findById(id);
        if (estudiante != null) {
            delete(estudiante);
        }
    }

    // Buscar estudiantes por nombre
    public List<Estudiante> findByNombreContaining(String nombre) {
        return entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.nombre LIKE :nombre", Estudiante.class)
                            .setParameter("nombre", "%" + nombre + "%")
                            .getResultList();
    }
}
