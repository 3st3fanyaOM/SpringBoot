package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Libro;

@RestController
@RequestMapping("/libros")
public class LibroController {

	List<Libro> libros = new ArrayList<>();

	LibroController() {

		List<String> generos1 = new ArrayList<>();
		Libro l1 = new Libro(001, "El ancho mar", "Arturo López", "El Sur", "ES22", 2009, generos1);
		l1.getGeneros().add("Terror");
		l1.getGeneros().add("Fantasia");
		List<String> generos2 = new ArrayList<>();
		Libro l2 = new Libro(002, "Camino a la locura", "Raul Mendez", "Editorial Gayo", "ES33", 2019, generos2);
		l2.getGeneros().add("Fantasia");
		l2.getGeneros().add("Ciencia Ficción");
		List<String> generos3 = new ArrayList<>();
		Libro l3 = new Libro(003, "Rosas y Lirios", "Eduardo López", "Castillo", "ES55", 1989, generos3);
		l3.getGeneros().add("Drama");
		l3.getGeneros().add("Fantasia");
		List<String> generos4 = new ArrayList<>();
		Libro l4 = new Libro(004, "Un asesinato", "Elena Márquez", "Castillo", "ES77", 2015, generos4);
		l4.getGeneros().add("Historia");
		l4.getGeneros().add("Ciencia Ficción");

		libros.add(l1);
		libros.add(l2);
		libros.add(l3);
		libros.add(l4);
	}

	@GetMapping // devuelve la lista de libros
	public ResponseEntity<List<Libro>> mostrarLibros() {
		return ResponseEntity.ok(libros);
	}

	@GetMapping("/{titulo}") // devuelve un libro por titulo
	public ResponseEntity<Libro> libroPorTitulo(@PathVariable String titulo) {
		for (Libro libro : libros) {
			if (libro.getTitulo().equalsIgnoreCase(titulo)) {
				return ResponseEntity.ok(libro);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping // añadir un libro
	public ResponseEntity<Libro> postLibro(@RequestBody Libro libro) {
		libros.add(libro);
		return ResponseEntity.noContent().build();
	}

	@PutMapping // actualiza un libro completo
	public ResponseEntity<Void> actualizarLibrocompleto(@RequestBody Libro libro) {
		for (Libro libro1 : libros) {
			if (libro1.getId() == libro.getId()) {
				libro1.setAnioPublicacion(libro.getAnioPublicacion());
				libro1.setAutor(libro.getAutor());
				libro1.setEditorial(libro.getEditorial());
				libro1.setIsbn(libro.getIsbn());
				libro1.setTitulo(libro.getTitulo());
				libro1.setGeneros(libro.getGeneros());

				return ResponseEntity.noContent().build();
			}
		}
		return null;
	}

	@DeleteMapping("/{id}") // borra libro por id
	public ResponseEntity<Void> borrarLibro(@PathVariable Integer id) {
		Iterator<Libro> it = libros.iterator();
		while (it.hasNext()) {
			Libro libro = it.next();
			if (libro.getId() == id) {
				it.remove();
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/novelas") // devuelve lista de novelas
	public ResponseEntity<List<Libro>> mostrarNovelas() {
		List<Libro> novelas = new ArrayList<>();
		for (Libro libro : libros) {
			if(libro.getGeneros().contains("Novela")) {
				novelas.add(libro);
			}
			return ResponseEntity.ok(novelas);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{genero}") // devuelve lista por genero
	public ResponseEntity<List<Libro>> mostrarPorGenero(@PathVariable String genero) {
		List<Libro> librosGenero = new ArrayList<>();
		for (Libro libro : libros) {
			for (String genero1 : libro.getGeneros()) {
				if (genero1.contentEquals(genero)) {
					librosGenero.add(libro);
					return ResponseEntity.ok(librosGenero);
				}
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/mas_de/{n}")//Devuelve mapa con autores que tengan más de x libros
	public ResponseEntity<Map<String,Integer>> mostrarAutoresConMasDe(@PathVariable Integer numLibros){
		Map<String,Integer> mapa = new HashMap<>();
		for(Libro libro: libros) {
			mapa.put(libro.getAutor(), libro.);
		}
	}

}
