package com.examenB.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.examenB.modelo.Reserva;
import com.examenB.servicio.ReservaServicio;

@RestController
@RequestMapping("/api/reservas")
public class ReservaControlador {
	
	
	@Autowired
	private ReservaServicio reservaService;
	
	// A) Crear una nueva reserva
	@PostMapping
    public ResponseEntity<Void> crearReserva(@RequestBody Reserva reserva) {
        reservaService.crearReserva(reserva);
        return ResponseEntity.noContent().build();
    }

	 // B) Obtener todas las entradas
    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodasReservas() {
        List<Reserva> reservas = reservaService.obtenerTodasReservas();
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 - Sin contenido
        }
        return ResponseEntity.ok(reservas); // 200 - OK
    }

    // C) Actualizar una entrada (por id)
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        reserva.setId(id);
        Reserva reservaActualizada = reservaService.actualizarReserva(id, reserva);
        if (reservaActualizada == null) {
            return ResponseEntity.notFound().build(); // 404 - No encontrado
        }
        return ResponseEntity.ok(reservaActualizada); // 204 - Sin contenido (no devuelve la entrada, solo éxito)
    }

 // D) Borra las canceladas
    @DeleteMapping("/cancelados")
    public ResponseEntity<String> eliminarReservasCanceladas() {
        Integer cont = reservaService.eliminarReservasCanceladas();
        if(cont==0) {
        	return ResponseEntity.ok("No se ha podido eliminar ninguna reserva");
        }
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/nuevoPrecio/{id}")
    public ResponseEntity<Reserva> modificarPrecio(@PathVariable Long id, @RequestBody Reserva reserva) {
    	 Reserva reservaActualizada = reservaService.modificarPrecioHabitacion(id, reserva);

         if (reservaActualizada == null) {
        	 return ResponseEntity.notFound().build();// Reserva no encontrada
         }

         return ResponseEntity.ok(reservaActualizada); // Devolver reserva modificada
    }

 // F) Obtener reservas con más de X noches
    @GetMapping("/reservasLargas/{noches}")
    public ResponseEntity<List<Reserva>> obtenerReservasLargas(@PathVariable Integer noches) {
        List<Reserva> reservas = reservaService.obtenerReservasLargas(noches);
        if (!reservas.isEmpty()) {
        	return ResponseEntity.ok(reservas);
        }
        return ResponseEntity.notFound().build();
        
    }

    // G) Obtener mapa de habitaciones reservadas
    @GetMapping("/mapaClientes")
    public ResponseEntity<Map<String, Integer>> obtenerMapaClientes() {
        Map<String, Integer> mapaClientes = reservaService.obtenerMapaClientes();
        if (mapaClientes.isEmpty()) {
            return ResponseEntity.notFound().build(); // 204 - Sin contenido
        }
        return ResponseEntity.ok(mapaClientes); // 200 - OK
    }

    // H) Modificar reserva
    @PatchMapping("/modificarReserva/{id}/{nombreCliente}/{numNoches}")
    public ResponseEntity<Reserva> modificarReserva(@PathVariable Long id, 
                                                   @PathVariable String nombreCliente, 
                                                   @PathVariable Integer numNoches) {
        Reserva reservaModificada = reservaService.modificarReserva(id, nombreCliente, numNoches);
        if (reservaModificada == null) {
            return ResponseEntity.notFound().build(); // 404 - No encontrado
        }
        return ResponseEntity.ok(reservaModificada);
    }

    // I) Obtener la reserva más barata
    @GetMapping("/masBarata")
    public ResponseEntity<Reserva> obtenerReservaMasBarata() {
        Reserva reservaMasBarata = reservaService.obtenerReservaMasBarata();
        return ResponseEntity.ok(reservaMasBarata);
    }

    // J) Obtener 3 reservas familiares activas
    @GetMapping("/reservasPorHabitacion/{tipoHabitacion}")
    public ResponseEntity<List<String>> obtenerMenorReservaFamiliar(@PathVariable String tipoHabitacion) {
        List<String> reservas = reservaService.reservasPorHabitacion(tipoHabitacion);
        if (reservas.isEmpty()) {
            return ResponseEntity.notFound().build(); // 204 - Sin contenido
        }
        return ResponseEntity.ok(reservas);
    }
	

}
