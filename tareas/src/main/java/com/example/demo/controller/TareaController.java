package com.example.demo.controller;

import java.time.LocalDate;
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

import com.example.demo.modelo.Tarea;

@RestController
@RequestMapping("/tareas")
public class TareaController {

	public static final String PENDIENTE = "PENDIENTE";
	public static final String ENPROCESO = "EN PROCESO";
	public static final String COMPLETA = "COMPLETA";

	List<Tarea> tareas = new ArrayList<>();

	TareaController() {
		Tarea t1 = new Tarea(001, "Reunión", "Reunión con proveedores", LocalDate.of(2024, 2, 3), PENDIENTE);
		Tarea t2 = new Tarea(002, "Visita", "Visita a cliente", LocalDate.of(2024, 4, 3), ENPROCESO);
		Tarea t3 = new Tarea(003, "Comprar", "Comprar material", LocalDate.of(2024, 6, 3), COMPLETA);
		Tarea t4 = new Tarea(004, "Reunión", "Reunión con jefe", LocalDate.of(2024, 11, 3), ENPROCESO);

		tareas.add(t1);
		tareas.add(t2);
		tareas.add(t3);
		tareas.add(t4);
	}

	@GetMapping
	public ResponseEntity<List<Tarea>> mostrarTareas() {
		if (tareas.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(tareas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tarea> tareaPorID(@PathVariable Integer id) {
		for (Tarea t : tareas) {
			if (t.getId() == id) {
				return ResponseEntity.ok(t);
			}
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Void> añadirTarea(@RequestBody Tarea tarea) {
		tareas.add(tarea);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<Void> modificarTareaCompleta(@RequestBody Tarea tarea) {
		for (Tarea t : tareas) {
			if (t.getId() == tarea.getId()) {
				t.setTitulo(tarea.getTitulo());
				t.setDescripcion(tarea.getDescripcion());
				t.setEstado(tarea.getEstado());
				t.setVencimiento(tarea.getVencimiento());
			}
		}
		return ResponseEntity.noContent().build();
	}

	@PatchMapping
	public ResponseEntity<Void> modificarTareaParcial(@RequestBody Tarea tarea) {
		for (Tarea t : tareas) {
			if (t.getId() == tarea.getId()) {
				if (t.getDescripcion() != null) {
					t.setDescripcion(tarea.getDescripcion());
				}
				if (t.getEstado() != null) {
					t.setEstado(tarea.getEstado());
				}
				if (t.getTitulo() != null) {
					t.setTitulo(tarea.getTitulo());
				}
				if (t.getVencimiento() != null) {
					t.setVencimiento(tarea.getVencimiento());
				}
			}
		}
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarTarea(@PathVariable Integer id) {
		Iterator<Tarea> it = tareas.iterator();
		while (it.hasNext()) {
			Tarea t = it.next();
			if (t.getId() == id) {
				it.remove();
				return ResponseEntity.noContent().build();
			}
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/estado/{estado}")
	public ResponseEntity<List<Tarea>> tareasPorEstado(@PathVariable String estado) {
		List<Tarea> tareasEstado = new ArrayList<>();
		for (Tarea t : tareas) {
			if (t.getEstado() == PENDIENTE) {
				tareasEstado.add(t);
			} else if (t.getEstado() == ENPROCESO) {
				tareasEstado.add(t);
			} else {
				tareasEstado.add(t);
			}
		}
		if (tareasEstado.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(tareasEstado);
	}

	@GetMapping("/proximas/{dias}")//tareas próximas a vencer
	public ResponseEntity<List<Tarea>> tareasProximas(@PathVariable Integer dias) {
		List<Tarea> tareasProximas = new ArrayList<>();
		for (Tarea t : tareas) {
			if (t.getVencimiento().minusDays(dias).isBefore(LocalDate.now())) {
				tareasProximas.add(t);
			}
		}
		if (tareasProximas.isEmpty())
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(tareasProximas);
	}

	@GetMapping("/ContarPorEstado")//contar tareas por estado
	public ResponseEntity<Integer> tareasEstado(@PathVariable String estado){
		Integer tareasPorEstado = 0;
		for (Tarea t :tareas) {
			if(t.getEstado().equalsIgnoreCase(estado)) {
				tareasPorEstado++;
			}
			if(t.getEstado().equalsIgnoreCase(estado)) {
				tareasPorEstado++;
			}
			if(t.getEstado().equalsIgnoreCase(estado)) {
				tareasPorEstado++;
			}
		}
		return ResponseEntity.ok(tareasPorEstado);
	}
	
	@GetMapping("/buscar/{palabraClave}")//tareas por palabra en descripcion 
	public ResponseEntity<Tarea> tareaPorPalabraClave(@PathVariable String palabra){
		for (Tarea t :tareas) {
			if(t.getDescripcion().contains(palabra)) {
				return ResponseEntity.ok(t);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PatchMapping//marcar tarea como completada PAtch /marcar-completadas
	public ResponseEntity<Void> marcarTareaCompletada(@RequestBody Tarea tarea){
		for (Tarea t :tareas) {
			if(t.getId()==tarea.getId()) {
				if(tarea.getEstado()!=null) {
					t.setEstado(COMPLETA);
					return ResponseEntity.noContent().build();
				}
			}
		}
		return ResponseEntity.notFound().build();
	}
}
