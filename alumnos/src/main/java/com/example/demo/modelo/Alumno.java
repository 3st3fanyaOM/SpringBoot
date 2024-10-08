package com.example.demo.modelo;

public class Alumno {

	private int id;
	private String name;
	private String email;
	private String curso;
	private Direccion direccion;
	
	public Alumno(int id, String name, String email, String curso,Direccion direccion) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.curso = curso;
		this.direccion=direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
	
	
}
