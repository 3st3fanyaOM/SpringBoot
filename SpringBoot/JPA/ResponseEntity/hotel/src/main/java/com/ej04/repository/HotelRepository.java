package com.ej04.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej04.model.Hotel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class HotelRepository  {
	@PersistenceContext
    private EntityManager entityManager;

	public Hotel crearHotel(Hotel hotel) {
        if (hotel.getId() == null) {
            entityManager.persist(hotel);  // Insertar nuevo hotel
        } else {
            hotel = entityManager.merge(hotel);  // Actualizar hotel existente
        }
        return hotel;
    }

    public Hotel obtenerHotel(Long id) {
        Hotel hotel = entityManager.find(Hotel.class, id);
        return hotel;
    }

    public List<Hotel> findAll() {
        
        return entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
    }

    public void deleteById(Long id) {
        Hotel hotel = entityManager.find(Hotel.class, id);
        if (hotel != null) {
            entityManager.remove(hotel);
        }
    }

    public List<Hotel> findByEstrellasBetween(int min, int max) {
        Query query =  (Query)entityManager.createQuery(
            "SELECT h FROM Hotel h WHERE h.estrellas BETWEEN :min AND :max", Hotel.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        
        return query.getResultList();
    }

    public List<Hotel> findByTelefono(String telefono) {
        Query query = entityManager.createQuery(
            "SELECT h FROM Hotel h WHERE h.telefono = :telefono", Hotel.class);
        query.setParameter("telefono", telefono);
        return query.getResultList();
    }

    public void borrarHotel(Hotel hotel) {       
        	entityManager.remove(hotel);       
    }

    public List<Hotel> saveAll(List<Hotel> hoteles) {
        for (Hotel hotel : hoteles) {
            entityManager.persist(hotel);
        }
        return hoteles;
    }
    
    // Buscar hotel por nombre
    public List<Hotel> buscarHotelPorNombre(String nombre) {
        // Usamos HQL para realizar la búsqueda con LIKE
        String hql = "FROM Hotel h WHERE h.nombre LIKE :nombre";
        return entityManager.createQuery(hql, Hotel.class)
                .setParameter("nombre", "%" + nombre + "%") // % para buscar coincidencias parciales
                .getResultList();
    }

}
