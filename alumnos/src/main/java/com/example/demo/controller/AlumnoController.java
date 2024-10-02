package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.modelo.Alumno;




@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	
	private List<Alumno> alumnos = new ArrayList<>();

	public AlumnoController() {
		Alumno a1 = new Alumno(01, "Pedro", "pedro01@hh.com", "1DAW");
		Alumno a2 = new Alumno(02, "Carlos", "carlos01@hh.com", "2DAM");
		Alumno a3 = new Alumno(03, "Maria", "maria01@hh.com", "2DAM");
		Alumno a4 = new Alumno(03, "Maria", "maria01@hh.com", "2DAW");

		alumnos.add(a1);
		alumnos.add(a2);
		alumnos.add(a3);
		alumnos.add(a4);

	}
	
	@GetMapping
	public List<Alumno> mostrarAlumnos(){
		return alumnos;
	}
	
	@GetMapping("/{email}")
	public Alumno alumnoEmail(@PathVariable String email) {
		for (Alumno alumno : alumnos) {
			if(alumno.getEmail().equalsIgnoreCase(email)) {
				return alumno;
			}
		}
		return null;
	}
	
	@PostMapping
	public Alumno addAlumno(@RequestBody Alumno alumno) {
		alumnos.add(alumno);
		return alumno;
	}
	
	@PutMapping
	public Alumno actualizarAlumnoCompleto(@RequestBody Alumno alumno) {
		for (Alumno alumno2 : alumnos) {
			if (alumno2.getId()==alumno.getId()) {
				alumno2.setCurso(alumno.getCurso());
				alumno2.setEmail(alumno.getEmail());
				alumno2.setName(alumno.getName());
			}
		}
		return alumno;
	}
	
	@DeleteMapping("/{id}")
	public Alumno borrarAlumno(@PathVariable int id) {
		Iterator<Alumno> it = alumnos.iterator();
		while(it.hasNext()) {
			Alumno alumno = it.next();
			if (alumno.getId()==id) {
				it.remove();
				return alumno;
			}
		}
		return null;
	}

	
	@PatchMapping
	public Alumno modificaAlumno(@RequestBody Alumno alumno) {
		for (Alumno alumno2 : alumnos) {
			if(alumno2.getId()==alumno.getId()) {
				if(alumno2.getName()!=null) {
					alumno2.setName(alumno.getName());
				}
				if(alumno2.getCurso()!=null) {
					alumno2.setCurso(alumno.getCurso());
				}
				if(alumno2.getEmail()!=null) {
					alumno2.setEmail(alumno.getEmail());
				}
				return alumno;
			}
			
		}
		return null;
	}
}
