package com.ej18;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ej18.model.Pasaporte;
import com.ej18.model.Persona;
import com.ej18.model.Proyecto;
import com.ej18.service.ProyectoService;

@SpringBootApplication
public class Ejercicio18Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio18Application.class, args);
	}

	@Autowired
	ProyectoService servicio;
	
	
	@Override
	public void run(String... args) throws Exception {
		 // Crear personas
        Persona persona1 = new Persona();
        persona1.setNombre("Juan Perez");
        servicio.guardarPersona(persona1);

        Persona persona2 = new Persona();
        persona2.setNombre("Ana Lopez");
        servicio.guardarPersona(persona2);

        // Crear pasaportes
        Pasaporte pasaporte1 = new Pasaporte();
        pasaporte1.setNumero("ABC123");
        servicio.guardarPasaporte(pasaporte1);

        Pasaporte pasaporte2 = new Pasaporte();
        pasaporte2.setNumero("XYZ456");
        servicio.guardarPasaporte(pasaporte2);

        // Crear proyectos
        Proyecto proyecto1 = new Proyecto();
        proyecto1.setNombre("Proyecto Alpha");
        servicio.guardarProyecto(proyecto1);

        Proyecto proyecto2 = new Proyecto();
        proyecto2.setNombre("Proyecto Beta");
        servicio.guardarProyecto(proyecto2);

        // Asignar pasaportes a personas
        servicio.asignarPasaporteAPersona(persona1, pasaporte1);
        servicio.asignarPasaporteAPersona(persona2, pasaporte2);

        // Asignar proyectos a personas
        servicio.asignarProyectoAPersona(persona1, proyecto1);
        
        servicio.asignarProyectoAPersona(persona1, proyecto2);
        servicio.asignarProyectoAPersona(persona2, proyecto1);

        // Obtener todas las personas con sus proyectos
        List<Persona> personas = servicio.obtenerTodasLasPersonas();
        personas.forEach(p -> {
            System.out.println("Persona: " + p.getNombre());
            p.getProyectos().forEach(pr -> System.out.println("  Proyecto: " + pr.getNombre()));
        });

        // Eliminar proyecto2 de persona1
        servicio.eliminarProyectoDePersona(persona1, proyecto2);

        // Obtener los datos actualizados de persona1
        Persona personaActualizada = servicio.obtenerPersonaPorId(persona1.getId());
        System.out.println("Persona actualizada: " + personaActualizada.getNombre());
        personaActualizada.getProyectos().forEach(pr -> System.out.println("  Proyecto: " + pr.getNombre()));
        
    }

}
