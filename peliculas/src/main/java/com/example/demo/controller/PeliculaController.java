package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.example.demo.modelo.Actor;
import com.example.demo.modelo.Pelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

	List<Pelicula> peliculas = new ArrayList<>();

	PeliculaController() {
		List<Actor> actores = new ArrayList<>();
		Actor a1 = new Actor(01, "Nombre 1", "Nacionalidad 1");
		Actor a2 = new Actor(02, "Nombre 2", "Nacionalidad 2");
		Actor a3 = new Actor(03, "Nombre 3", "Nacionalidad 3");
		Pelicula p1 = new Pelicula(01, "Pelicula 1", "Director 1", LocalDate.of(2000, 3, 1), 126, actores);
		p1.getActores().add(a1);
		p1.getActores().add(a3);
		Pelicula p2 = new Pelicula(02, "Pelicula 2", "Director 2", LocalDate.of(2001, 4, 1), 130, actores);
		p2.getActores().add(a2);
		p2.getActores().add(a3);
		Pelicula p3 = new Pelicula(03, "Pelicula 3", "Director 3", LocalDate.of(2005, 4, 1), 150, actores);
		p3.getActores().add(a2);
		p3.getActores().add(a1);

		peliculas.add(p1);
		peliculas.add(p2);
		peliculas.add(p3);
	}

	@GetMapping
	public ResponseEntity<List<Pelicula>> listarPeliculas() {
		if (peliculas.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(peliculas);
	}

	@GetMapping("/{titulo}")
	public ResponseEntity<Pelicula> peliculasPorTitulo(@PathVariable String titulo) {
		for (Pelicula p : peliculas) {
			if (p.getTitulo().equalsIgnoreCase(titulo)) {
				return ResponseEntity.ok(p);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Void> addPelicula(@RequestBody Pelicula pelicula) {
		peliculas.add(pelicula);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<Void> actualizarPeliculaCompleta(@RequestBody Pelicula pelicula) {
		for (Pelicula p : peliculas) {
			if (p.getId() == pelicula.getId()) {
				p.setActores(pelicula.getActores());
				p.setDirector(pelicula.getDirector());
				p.setDuracion(pelicula.getDuracion());
				p.setLanzamiento(pelicula.getLanzamiento());
				p.setTitulo(pelicula.getTitulo());
				return ResponseEntity.noContent().build();
			}
		}
		return null;
	}

	@PatchMapping
	public ResponseEntity<Void> actualizarPeliParcial(@RequestBody Pelicula pelicula) {
		for (Pelicula p : peliculas) {
			if (p.getId() == pelicula.getId()) {
				if (p.getActores() != null) {
					p.setActores(pelicula.getActores());
				}
				if (p.getDirector() != null) {
					p.setDirector(pelicula.getDirector());
				}
				if (p.getDuracion() != 0) {
					p.setDuracion(pelicula.getDuracion());
				}
				if (p.getLanzamiento() != null) {
					p.setLanzamiento(pelicula.getLanzamiento());
				}
				if (p.getTitulo() != null) {
					p.setTitulo(pelicula.getTitulo());
				}

				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarPelicula(@PathVariable Integer id) {
		Iterator<Pelicula> it = peliculas.iterator();
		while (it.hasNext()) {
			Pelicula p = it.next();
			if (p.getId() == id) {
				it.remove();
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{director}")
	public ResponseEntity<List<Pelicula>> peliculasPorDirector(@PathVariable String director) {
		List<Pelicula> pDirector = new ArrayList<>();
		for (Pelicula p : peliculas) {
			if (p.getDirector().equalsIgnoreCase(director)) {
				pDirector.add(p);
			}
		}
		if (pDirector.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(pDirector);
	}

	@GetMapping
	public ResponseEntity<List<Pelicula>> peliculasCincoAños() {
		List<Pelicula> pCincoAnios = new ArrayList<>();
		for (Pelicula p : peliculas) {
			if (p.getLanzamiento().isAfter(LocalDate.now().minusYears(5))) {
				pCincoAnios.add(p);
			}
		}
		if (pCincoAnios.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(pCincoAnios);
	}

	@GetMapping
	public ResponseEntity<Pelicula> peliculaMasDuracion() {
		Pelicula pMasLarga = null;
		for (Pelicula p : peliculas) {
			if (p.getDuracion() > pMasLarga.getDuracion()) {
				pMasLarga = p;
				return ResponseEntity.ok(pMasLarga);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/directores/conMasDe/{n}")
	public Map<String, Integer> dirMAsDeXPelis(@PathVariable int nPelis) {
		Map<String, Integer> contador = new HashMap<>();
		for (Pelicula p : peliculas) {
			String director = p.getDirector();
			contador.put(director, contador.getOrDefault(director, 0) + 1);
		}

		Map<String, Integer> peliculasPorDirector = new HashMap<>();
		for (Map.Entry<String, Integer> entry : contador.entrySet()) {
			if (entry.getValue() > nPelis) {
				peliculasPorDirector.put(entry.getKey(), entry.getValue());
			}
		}
		return peliculasPorDirector;
	}

	@GetMapping
	public ResponseEntity<Set<String>> actoresSinRepetir() {
		Set<String> cjtoActores = new HashSet<>();
		for (Pelicula p : peliculas) {
			for (Actor a : p.getActores()) {
				cjtoActores.add(a.getNombre());
			}
		}
		if (cjtoActores.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(cjtoActores);
	}

	@GetMapping("/{actor}")
	public ResponseEntity<List<Pelicula>> peliculasPorActor(@PathVariable String actor) {
		List<Pelicula> pelisPorActor = new ArrayList<>();
		for (Pelicula p : peliculas) {
			for (Actor a : p.getActores()) {
				if (a.getNombre().equalsIgnoreCase(actor)) {
					pelisPorActor.add(p);
				}
			}
		}
		if (pelisPorActor.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(pelisPorActor);
	}

	@GetMapping("/actoresPorNac/{nac}")
	public ResponseEntity<List<Actor>> actoresPorNac(@PathVariable String nac) {
		List<Actor> ActoresNac = new ArrayList<>();
		for (Pelicula p : peliculas) {
			for (Actor a : p.getActores()) {
				if (a.getNacionalidad().equalsIgnoreCase(nac)) {
					ActoresNac.add(a);
				}
			}
		}
		if (ActoresNac.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(ActoresNac);
	}

}
