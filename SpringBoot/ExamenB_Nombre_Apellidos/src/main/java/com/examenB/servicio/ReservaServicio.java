package com.examenB.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenB.modelo.Reserva;
import com.examenB.repositorio.ReservaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ReservaServicio {

	@Autowired
	private ReservaRepositorio reservaRepositorio;

	@Transactional
	public void crearReserva(Reserva reserva) {
		double total = calcularPrecioTotal(reserva);
		reserva.setPrecioTotal(total);
		reservaRepositorio.guardar(reserva);
	}

	public List<Reserva> obtenerTodasReservas() {
		return reservaRepositorio.obtenerTodas();
	}

	@Transactional
	public Reserva actualizarReserva(Long id, Reserva nuevaReserva) {
		Reserva reserva = reservaRepositorio.encontrarPorId(id);
		if (reserva != null) {
			reserva.setActiva(nuevaReserva.getActiva());
			reserva.setNombreCliente(nuevaReserva.getNombreCliente());
			reserva.setTipoHabitacion(nuevaReserva.getTipoHabitacion());
			reserva.setNumeroNoches(nuevaReserva.getNumeroNoches());
			reserva.setPrecioHabitacion(nuevaReserva.getPrecioHabitacion());
			reserva.setCodigoVenta(nuevaReserva.getCodigoVenta());
			reserva.setPrecioTotal(nuevaReserva.getPrecioTotal());
		
			reservaRepositorio.actualizar(reserva);
		}
		return reserva;
	}

	@Transactional
	public Integer eliminarReservasCanceladas() {
		List<Reserva> canceladas = reservaRepositorio.obtenerReservasCanceladas();
		Integer cont =0;
		for (Reserva reserva : canceladas) {
			reservaRepositorio.eliminar(reserva);
			cont++;
		}
		return cont;
	}

	private double calcularPrecioTotal(Reserva reserva) {
		double total = reserva.getPrecioHabitacion() * reserva.getNumeroNoches();
		if ("Familiar".equalsIgnoreCase(reserva.getTipoHabitacion())) {
			
			//Double precioRebajado = (precioTotal * 3) / 100;
			//Double precioFinal = precioTotal - precioRebajado;
			
			total *= 0.97; // Descuento del 3%
		}
		return total;

	}

	@Transactional
	public Reserva modificarPrecioHabitacion(Long id, Reserva r) {
		// Buscar la reserva por ID
		Reserva reservaExistente = reservaRepositorio.encontrarPorId(id);
		if (reservaExistente != null) {
			// Actualizar el precio de la habitación
			reservaExistente.setPrecioHabitacion(r.getPrecioHabitacion());
			// Recalcular el precio total
			double total = calcularPrecioTotal(reservaExistente);
			reservaExistente.setPrecioTotal(total);
			// Guardar la reserva actualizada
			reservaRepositorio.actualizar(reservaExistente);
		}
		return reservaExistente;
	}

	// F) Obtener reservas con más de X noches
	public List<Reserva> obtenerReservasLargas(int noches) {
		return reservaRepositorio.findReservasLargas(noches);
	}

	// G) Obtener mapa de habitaciones reservadas
	public Map<String, Integer> obtenerMapaClientes() {
		Map<String, Integer> mapaClientes = new HashMap<>();

		List<Reserva> reservas = reservaRepositorio.obtenerTodas();
		// Iterar sobre las reservas para contar las activas por tipo de habitación
		for (Reserva reserva : reservas) {
			if (reserva.getActiva()) { // Solo contar reservas activas
				String tipoHabitacion = reserva.getTipoHabitacion();
				// Obtener el valor actual del mapa o 0 si no existe
				int count = mapaClientes.getOrDefault(tipoHabitacion, 0);
				// Incrementar el contador para ese tipo de habitación
				mapaClientes.put(tipoHabitacion, count + 1);
			}
		}
		return mapaClientes;
	}

	// H) Modificar reserva
	@Transactional
	public Reserva modificarReserva(Long id, String nombreCliente, Integer numNoches) {
		Reserva reserva = reservaRepositorio.encontrarPorId(id);
		
		  if (reserva == null) {
	            return null; // No existe 
	        }
		  
		reserva.setNombreCliente(nombreCliente);
		reserva.setNumeroNoches(numNoches);
		reservaRepositorio.actualizar(reserva);
		return reserva;
	}

	// I) Obtener la reserva más barata
	public Reserva obtenerReservaMasBarata() {
		List<Reserva> reservas = reservaRepositorio.obtenerTodas(); // Obtener todas las reservas desde la base de datos

		if (reservas.isEmpty()) {
			return null;
		}

		Reserva reservaMasBarata = reservas.get(0); // Inicializamos con la primera reserva como la más barata

		// Recorrer las reservas para encontrar la más barata
		for (Reserva reserva : reservas) {
			if (reserva.getPrecioTotal() < reservaMasBarata.getPrecioTotal()) {
				reservaMasBarata = reserva; // Actualizar la reserva más barata
			}
		}

		return reservaMasBarata;
	}

	// J) Obtener 3 reservas familiares activas
	public List<String> reservasPorHabitacion(String tipoHabitacion) {
		// Obtener todas las reservas activas
		List<Reserva> reservas = reservaRepositorio.obtenerTodas();
		List<Reserva> entradasFiltradas = reservas.stream()
				.filter(reserva -> reserva.getActiva() && reserva.getTipoHabitacion().equals(tipoHabitacion)) // Filtrar habitación
				.toList();

		// Mostrar todas las que cumplen por consola
		entradasFiltradas.forEach(System.out::println);

		// Obtener las primeras 3 reservas
		return reservas.stream()
				.filter(reserva -> reserva.getActiva() && reserva.getTipoHabitacion().equals(tipoHabitacion)) // Filtrar habitación
				.limit(3) // Limitar a las primeras 3
				.map(Reserva::toString).toList();
	}

}
