package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
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
import com.example.demo.modelo.Direccion;




@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	
	private List<Alumno> alumnos = new ArrayList<>();
	Direccion d1 = new Direccion("Francisco Ayala", "41909","Sevilla");
	Direccion d2 = new Direccion("Mojao", "41908","Sevilla");
	Direccion d3 = new Direccion("Mayo", "41903","Sevilla");
	Direccion d4 = new Direccion("larga", "41989","Sevilla");

	public AlumnoController() {
		Alumno a1 = new Alumno(01, "Pedro", "pedro01@hh.com", "1DAW",d1);
		Alumno a2 = new Alumno(02, "Carlos", "carlos01@hh.com", "2DAM",d2);
		Alumno a3 = new Alumno(03, "Maria", "maria01@hh.com", "2DAM",d3);
		Alumno a4 = new Alumno(03, "Maria", "maria01@hh.com", "2DAW",d4);

		alumnos.add(a1);
		alumnos.add(a2);
		alumnos.add(a3);
		alumnos.add(a4);

	}
	
	@GetMapping//devuelve la lista de alumnos
	public ResponseEntity<List<Alumno>> mostrarAlumnos(){
		return ResponseEntity.ok(alumnos);
	}
	
	@GetMapping//devuelve la lista de direcciones
	public ResponseEntity<List<Direccion>> mostrarDirecciones(){
		List<Direccion> direcciones = new ArrayList<>();
		for(Alumno alumno:alumnos) {
			direcciones.add(alumno.getDireccion());
		}
		return ResponseEntity.ok(direcciones);
	}

	
	@GetMapping("/{email}")//buscar alumno por email
	public ResponseEntity<Alumno> alumnoEmail(@PathVariable String email) {
		for (Alumno alumno : alumnos) {
			if(alumno.getEmail().equalsIgnoreCase(email)) {
				return ResponseEntity.ok(alumno);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping//añadir alumno
	public ResponseEntity<Void> addAlumno(@RequestBody Alumno alumno) {
		alumnos.add(alumno);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping//actualiza alumno completo
	public ResponseEntity<Void> actualizarAlumnoCompleto(@RequestBody Alumno alumno) {
		for (Alumno alumno2 : alumnos) {
			if (alumno2.getId()==alumno.getId()) {
				alumno2.setCurso(alumno.getCurso());
				alumno2.setEmail(alumno.getEmail());
				alumno2.setName(alumno.getName());
				
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{id}")//borrar alumno por su id
	public ResponseEntity<Void> borrarAlumno(@PathVariable int id) {
		Iterator<Alumno> it = alumnos.iterator();
		while(it.hasNext()) {
			Alumno alumno = it.next();
			if (alumno.getId()==id) {
				it.remove();
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	
	@PatchMapping//modificar 1 o varios campos de alumno
	public ResponseEntity<Void> modificaAlumno(@RequestBody Alumno alumno) {
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
				return ResponseEntity.noContent().build();
			}
			
		}
		return ResponseEntity.notFound().build();
	}
}
