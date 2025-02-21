package com.ej03.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej03.model.Vehiculo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class VehiculoRepository  {
	@PersistenceContext
    private EntityManager entityManager;

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        entityManager.persist(vehiculo);
        return vehiculo;
    }

    public Vehiculo obtenerVehiculoPorId(Long id) {
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, id);
        return vehiculo;
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return entityManager.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class).getResultList();
    }

    public Vehiculo actualizarVehiculo(Vehiculo vehiculo) {
        return entityManager.merge(vehiculo);
    }

    public boolean eliminarVehiculo(Long id) {
        Vehiculo vehiculo = entityManager.find(Vehiculo.class, id);
        if (vehiculo != null) {
            entityManager.remove(vehiculo);
            return true;
        }
        return false;
    }

    public List<Vehiculo> consultarVehiculosPorEstado(String estado) {
        return entityManager.createQuery("SELECT v FROM Vehiculo v WHERE LOWER(v.estado) = LOWER(:estado)", Vehiculo.class)
                .setParameter("estado", estado)
                .getResultList();
    }

    public List<Vehiculo> filtrarVehiculosPorAnio(Integer startYear, Integer endYear) {
        return entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.anyo>= :startYear AND anyo <= :endYear", Vehiculo.class)
                .setParameter("startYear", startYear)
                .setParameter("endYear", endYear)
                .getResultList();
    }

}
