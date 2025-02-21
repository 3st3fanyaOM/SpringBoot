package com.ej11.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ubicacion;

    private String telefono;

    @OneToMany(mappedBy="oficina", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)  
    private List<Empleado> empleados;

    
    public Oficina(String ubicacion, String telefono) {
		super();
		this.ubicacion = ubicacion;
		this.telefono = telefono;	
		this.empleados = new ArrayList<>();
	}

	public Oficina() {
		super();
	}

	// Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

	@Override
	public String toString() {
		return "Oficina [id=" + id + ", ubicacion=" + ubicacion + ", telefono=" + telefono ;
	}
}
