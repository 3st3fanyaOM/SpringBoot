package com.example.demo.controller;

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

import com.example.demo.model.Album;
import com.example.demo.model.Artista;
import com.example.demo.model.Cancion;

@RestController
@RequestMapping("/albums")
public class AlbumController {

	List<Album> albums = new ArrayList<>();

	AlbumController() {

		List<Cancion> canciones = new ArrayList<>();
		List<Artista> artistas = new ArrayList<>();

		Cancion c1 = new Cancion(1, "Cancion 1", artistas);
		Cancion c2 = new Cancion(2, "Cancion 2", artistas);
		Cancion c3 = new Cancion(3, "Cancion 3", artistas);

		canciones.add(c3);
		canciones.add(c2);
		canciones.add(c1);

		Artista ar1 = new Artista(1, "Artista 1", "Nac 1", canciones);
		Artista ar2 = new Artista(2, "Artista 2", "Nac 2", canciones);
		Artista ar3 = new Artista(3, "Artista 3", "Nac 1", canciones);

		artistas.add(ar3);
		artistas.add(ar2);
		artistas.add(ar1);

		Album a1 = new Album(1, "Titulo 1", ar1, 2018, canciones);
		Album a2 = new Album(2, "Titulo 2", ar2, 2020, canciones);
		Album a3 = new Album(3, "Titulo 3", ar3, 2013, canciones);

		albums.add(a3);
		albums.add(a2);
		albums.add(a1);
	}

	@GetMapping // Mostrar todos los álbumes
	public ResponseEntity<List<Album>> mostrarAlbums() {
		if (albums.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(albums);

	}

	@GetMapping("/{titulo}") // álbum por su título
	public ResponseEntity<Album> albumPorTitulo(@PathVariable String titulo) {
		for (Album a : albums) {
			if (a.getTitulo().equalsIgnoreCase(titulo)) {
				return ResponseEntity.ok(a);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping // Crear un nuevo álbum.
	public ResponseEntity<Void> addAlbum(@RequestBody Album album) {
		albums.add(album);
		return ResponseEntity.noContent().build();
	}

	@PutMapping // Modificar la información de un álbum completa
	public ResponseEntity<Void> modificarAlbumCompleto(@RequestBody Album album) {
		for (Album a : albums) {
			if (a.getId() == album.getId()) {
				a.setAnioLanzamiento(album.getAnioLanzamiento());
				a.setArtistaPrincipal(album.getArtistaPrincipal());
				a.setCanciones(album.getCanciones());
				a.setTitulo(album.getTitulo());
				return ResponseEntity.noContent().build();
			}
		}
		return null;
	}

	@PatchMapping // Modificar la información de un álbum de manera parcial.
	public ResponseEntity<Void> modifAlbumParcial(@RequestBody Album album) {
		for (Album a : albums) {
			if (a.getId() == album.getId()) {
				if (album.getAnioLanzamiento() != 0) {
					a.setAnioLanzamiento(album.getAnioLanzamiento());
				}
				if (album.getArtistaPrincipal() != null) {
					a.setArtistaPrincipal(album.getArtistaPrincipal());
				}
				if (album.getCanciones() != null) {
					a.setCanciones(album.getCanciones());
				}
				if (album.getTitulo() != null) {
					a.setTitulo(album.getTitulo());
				}
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping // Eliminar un álbum por su id.
	public ResponseEntity<Void> borrarAlbum(@PathVariable Integer id) {
		Iterator<Album> it = albums.iterator();
		while (it.hasNext()) {
			Album a = it.next();
			if (a.getId() == id) {
				it.remove();
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cancionesPorAlbum/{album}") // Obtener todas las canciones de un álbum específico.
	public ResponseEntity<List<Cancion>> cancionesPorAlbum(@PathVariable Album album) {
		List<Cancion> cancionesPorAlbum = new ArrayList<>();
		for (Album a : albums) {
			for (Cancion c : a.getCanciones()) {
				cancionesPorAlbum.add(c);
				return ResponseEntity.ok(cancionesPorAlbum);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cancionesPorArtista/{artista}") // Obtener todas las canciones de un artista específico.
	public ResponseEntity<List<Cancion>> cancionesPorArtista(@PathVariable String art) {
		List<Cancion> cancionesPorArtista = new ArrayList<>();
		for (Album a : albums) {
			for (Cancion c : a.getCanciones()) {
				for (Artista ar : c.getArtistas()) {
					if (ar.getNombre().equalsIgnoreCase(art)) {
						cancionesPorArtista.add(c);
					}
				}
				return ResponseEntity.ok(cancionesPorArtista);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/artistasDeUnaCancion/{cancion}") // artistas de unacanción específica.
	public ResponseEntity<List<Artista>> artistasPorCancion(@PathVariable String cancion) {
		List<Artista> ArtistasDeCancion = new ArrayList<>();
		for (Album a : albums) {
			for (Cancion c : a.getCanciones()) {
				if (c.getTitulo().equalsIgnoreCase(cancion)) {
					for (Artista ar : c.getArtistas()) {
						ArtistasDeCancion.add(ar);
					}
				}
				return ResponseEntity.ok(ArtistasDeCancion);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/artistas") // artistas sin repetir ninguno.
	public ResponseEntity<Set<Artista>> actoresSinRepetir() {
		Set<Artista> cjtoArtistas = new HashSet<>();
		for (Album a : albums) {
			for (Cancion c : a.getCanciones()) {
				for (Artista ar : c.getArtistas()) {
					cjtoArtistas.add(ar);
				}
			}
		}

		if (cjtoArtistas.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(cjtoArtistas);
	}

	@GetMapping("/artistas/conMasDe/{n}") // ArtistasConNumeroDeCanciones.
	public Map<String, Integer> artistasConMasDXCaciones(@PathVariable int n) {
		Map<String, Integer> contador = new HashMap<>();
		for (Album a : albums) {
			for (Cancion c : a.getCanciones()) {
				for (Artista ar : c.getArtistas()) {
					contador.put(ar.getNombre(), contador.getOrDefault(ar.getNombre(), 0) + 1);
				}
			}

		}

		Map<String, Integer> cancionesPorArtista = new HashMap<>();
		for (Map.Entry<String, Integer> entry : contador.entrySet()) {
			if (entry.getValue() > n) {
				cancionesPorArtista.put(entry.getKey(), entry.getValue());
			}
		}
		return cancionesPorArtista;
	}

}
