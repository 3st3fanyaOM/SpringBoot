package com.ej04.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ej04.model.Hotel;
import com.ej04.service.HotelService;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping
	public ResponseEntity<Hotel> crearHotel(@RequestBody Hotel hotel) {
		Hotel nuevoHotel = hotelService.crearHotel(hotel);
		return ResponseEntity.ok(nuevoHotel);
	}

	@GetMapping
	public ResponseEntity<List<Hotel>> obtenerTodosLosHoteles() {
		List<Hotel> hoteles = hotelService.obtenerTodosLosHoteles();
		if (hoteles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(hoteles);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Hotel> obtenerHotelPorId(@PathVariable Long id) {
		Hotel hotel = hotelService.obtenerHotelPorId(id);
		return hotel == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(hotel);     
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<Hotel> actualizarHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
		hotel.setId(id);
		Hotel hotelActualizado = hotelService.actualizarHotel(hotel);
		return ResponseEntity.ok(hotelActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarHotel(@PathVariable Long id) {
		boolean eliminado = hotelService.eliminarHotel(id);
		if (eliminado) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/estrellas/{min}/{max}")
	public ResponseEntity<List<Hotel>> obtenerHotelesPorEstrellas(@PathVariable int min, @PathVariable int max) {
		List<Hotel> hoteles = hotelService.obtenerHotelesPorEstrellas(min, max);
		if (hoteles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(hoteles);
	}

	@GetMapping("/telefono/{telefono}")
	public ResponseEntity<Optional<Hotel>> buscarHotelPorTelefono(@PathVariable String telefono) {
		Optional<Hotel> hotel = hotelService.buscarHotelPorTelefono(telefono);
		return hotel.isPresent() ? ResponseEntity.ok(hotel) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/nombre/{nombre}")
	public ResponseEntity<Void> eliminarHotelesPorNombre(@PathVariable String nombre) {
		hotelService.eliminarHotelesPorNombre(nombre);
		
		return ResponseEntity.noContent().build();
		
	}

	@PostMapping("/lista")
	public ResponseEntity<List<Hotel>> crearHoteles(@RequestBody List<Hotel> hoteles) {
		List<Hotel> hotelesCreados = hotelService.crearHoteles(hoteles);
		return ResponseEntity.ok(hotelesCreados);
	}
}
