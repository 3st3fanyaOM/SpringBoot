package com.ej05.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej05.model.Animal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class AnimalRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Guardar un nuevo animal
    public void save(Animal animal) {
        entityManager.persist(animal);
    }

    // Obtener todos los animales
    public List<Animal> findAll() {
        return entityManager.createQuery("FROM Animal", Animal.class).getResultList();
    }

    // Obtener un animal por su ID
    public Animal findById(Long id) {
        return entityManager.find(Animal.class, id);
    }

    // Actualizar un animal
    public Animal update(Animal animal) {
        return entityManager.merge(animal);
    }

    // Eliminar un animal por ID
    public void deleteById(Long id) {
        Animal animal = findById(id);
        if (animal != null) {
            entityManager.remove(animal);
        }
    }

    // Buscar animales por especie
    public List<Animal> findByEspecie(String especie) {
        return entityManager.createQuery("FROM Animal a WHERE a.especie = :especie", Animal.class)
                .setParameter("especie", especie)
                .getResultList();
    }

    // Buscar animales por edad
    public List<Animal> findByEdad(Integer edad) {
        return entityManager.createQuery("FROM Animal a WHERE a.edad = :edad", Animal.class)
                .setParameter("edad", edad)
                .getResultList();
    }

    // Buscar animales recientes (ingresados en los últimos X años)
    public List<Animal> findRecentAnimals(Integer year) {
        return entityManager.createQuery("FROM Animal a WHERE YEAR(a.fechaIngreso) >= :year", Animal.class)
                .setParameter("year", year)
                .getResultList();
    }
}