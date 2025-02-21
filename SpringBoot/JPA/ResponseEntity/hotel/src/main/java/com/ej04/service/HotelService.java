package com.ej04.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej04.model.Hotel;
import com.ej04.repository.HotelRepository;

import jakarta.transaction.Transactional;


@Service
public class HotelService  {

	@Autowired
	private HotelRepository hotelRepository;

	@Transactional
    public Hotel crearHotel(Hotel hotel) {
        return hotelRepository.crearHotel(hotel);
    }

    public List<Hotel> obtenerTodosLosHoteles() {
        return hotelRepository.findAll();
    }

    public Hotel obtenerHotelPorId(Long id) {
        return hotelRepository.obtenerHotel(id);
    }

    @Transactional
    public Hotel actualizarHotel(Hotel hotel) {
        return hotelRepository.crearHotel(hotel);
    }

    @Transactional
    public boolean eliminarHotel(Long id) {
        hotelRepository.deleteById(id);
        return true;
    }

    public List<Hotel> obtenerHotelesPorEstrellas(int min, int max) {
        return hotelRepository.findByEstrellasBetween(min, max);
    }

    public Optional<Hotel> buscarHotelPorTelefono(String telefono) {
    	List<Hotel> resultado = hotelRepository.findByTelefono(telefono);
    	
    	return resultado.stream().findFirst();
    	
    }

    @Transactional
    public void eliminarHotelesPorNombre(String nombre) {
    	
    	List<Hotel> resultado= hotelRepository.buscarHotelPorNombre(nombre);
    	// Eliminar los hoteles encontrados
        for (Hotel hotel : resultado) {
        	hotelRepository.borrarHotel(hotel);
        }    	
    }

    @Transactional
    public List<Hotel> crearHoteles(List<Hotel> hoteles) {
        return hotelRepository.saveAll(hoteles);
    }
}
