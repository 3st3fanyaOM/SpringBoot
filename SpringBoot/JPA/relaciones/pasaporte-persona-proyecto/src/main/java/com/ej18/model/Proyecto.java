package com.ej18.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @ManyToMany(mappedBy = "proyectos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Persona> personas = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(Set<Persona> personas) {
		this.personas = personas;
	}

        
}
