package com.ej05.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ej05.model.Animal;
import com.ej05.service.AnimalService;

@RestController
@RequestMapping("/api/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Crear un nuevo animal
    @PostMapping
    public ResponseEntity<Animal> crearAnimal(@RequestBody Animal animal) {
        animalService.crearAnimal(animal);
    	return ResponseEntity.ok(animal);
    }

    // Obtener todos los animales
    @GetMapping
    public ResponseEntity<List<Animal>> obtenerTodosLosAnimales() {
        List<Animal> animales = animalService.obtenerTodosLosAnimales();
        return ResponseEntity.ok(animales);
    }

    // Obtener un animal específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Animal> obtenerAnimalPorId(@PathVariable Long id) {
        Animal animal = animalService.obtenerAnimalPorId(id);
        if (animal != null) {
            return ResponseEntity.ok(animal);
        }
        return ResponseEntity.notFound().build();  // 404 Not Found
    }

    // Actualizar un animal existente
    @PutMapping("/{id}")
    public ResponseEntity<Animal> actualizarAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.actualizarAnimal(id, animal);
        if (updatedAnimal != null) {
            return ResponseEntity.ok(updatedAnimal);
        }
        return ResponseEntity.notFound().build();  // 404 Not Found
    }

    // Eliminar un animal por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAnimal(@PathVariable Long id) {
        animalService.eliminarAnimal(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }

    // Buscar animales por especie
    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Animal>> obtenerAnimalesPorEspecie(@PathVariable String especie) {
        List<Animal> animales = animalService.obtenerAnimalesPorEspecie(especie);
        if (!animales.isEmpty()) {
            return ResponseEntity.ok(animales);
        }
        return ResponseEntity.notFound().build();  // 404 Not Found
    }

    // Buscar animales por edad (por ejemplo, 10 años)
    @GetMapping("/edad/{edad}")
    public ResponseEntity<List<Animal>> obtenerAnimalesPorEdad(@PathVariable Integer edad) {
        List<Animal> animales = animalService.obtenerAnimalesPorEdad(edad);
        return ResponseEntity.ok(animales);
    }

    // Buscar animales recientes (ingresados en los últimos X años)
    @GetMapping("/recientes/{anyo}")
    public ResponseEntity<List<Animal>> obtenerAnimalesRecientes(@PathVariable Integer anyo) {
        List<Animal> animales = animalService.obtenerAnimalesRecientes(anyo);
        return ResponseEntity.ok(animales);
    }
}