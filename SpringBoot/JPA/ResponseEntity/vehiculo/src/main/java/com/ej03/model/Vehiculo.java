package com.ej03.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehiculo {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String marca;

	    @Column(nullable = false)
	    private String modelo;

	    @Column(nullable = false)
	    private Integer anyo;

	    @Column(nullable = false, unique = true)
	    private String matricula;

	    @Column(nullable = false)
	    private String estado;

	    @Column(nullable = false)
	    private Double kilometraje;

	    // Getters y Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getMarca() {
	        return marca;
	    }

	    public void setMarca(String marca) {
	        this.marca = marca;
	    }

	    public String getModelo() {
	        return modelo;
	    }

	    public void setModelo(String modelo) {
	        this.modelo = modelo;
	    }

	    public Integer getAnyo() {
	        return anyo;
	    }

	    public void setAnyo(Integer anyo) {
	        this.anyo = anyo;
	    }

	    public String getMatricula() {
	        return matricula;
	    }

	    public void setMatricula(String matricula) {
	        this.matricula = matricula;
	    }

	    public String getEstado() {
	        return estado;
	    }

	    public void setEstado(String estado) {
	        this.estado = estado;
	    }

	    public Double getKilometraje() {
	        return kilometraje;
	    }

	    public void setKilometraje(Double kilometraje) {
	        this.kilometraje = kilometraje;
	    }
}