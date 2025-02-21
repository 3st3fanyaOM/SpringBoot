package com.ej05.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej05.model.Animal;
import com.ej05.repository.AnimalRepository;

import jakarta.transaction.Transactional;



@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public void crearAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    // Obtener todos los animales
    public List<Animal> obtenerTodosLosAnimales() {
        return animalRepository.findAll();
    }

    // Obtener un animal por su ID
    public Animal obtenerAnimalPorId(Long id) {
        return animalRepository.findById(id);
    }

    // Actualizar un animal
    public Animal actualizarAnimal(Long id, Animal animal) {
        Animal existingAnimal = animalRepository.findById(id);
        if (existingAnimal != null) {
            animal.setId(id);
            return animalRepository.update(animal);
        }
        return null;
    }

    // Eliminar un animal por ID
    public void eliminarAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    // Buscar animales por especie
    public List<Animal> obtenerAnimalesPorEspecie(String especie) {
        return animalRepository.findByEspecie(especie);
    }

    // Buscar animales por edad
    public List<Animal> obtenerAnimalesPorEdad(Integer edad) {
        return animalRepository.findByEdad(edad);
    }

    // Buscar animales recientes
    public List<Animal> obtenerAnimalesRecientes(Integer year) {
        return animalRepository.findRecentAnimals(year);
    }
}