package com.examenB.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.examenB.modelo.Reserva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ReservaRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    public void guardar(Reserva reserva) {
        entityManager.persist(reserva);
    }

    public Reserva encontrarPorId(Long id) {
        return entityManager.find(Reserva.class, id);
    }

    public List<Reserva> obtenerTodas() {
        return entityManager.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
    }

    public void actualizar(Reserva reserva) {
        entityManager.merge(reserva);
    }

    public void eliminar(Reserva reserva) {
        entityManager.remove(reserva);
    }

    public List<Reserva> obtenerReservasCanceladas() {
        return entityManager.createQuery("SELECT r FROM Reserva r WHERE r.activa = FALSE", Reserva.class).getResultList();
    }
    
    public List<Reserva> findReservasLargas(int noches) {
        // Consulta HQL para obtener reservas con más de X noches
        String hql = "FROM Reserva r WHERE r.numeroNoches > :noches";
        TypedQuery<Reserva> query = entityManager.createQuery(hql, Reserva.class);
        query.setParameter("noches", noches);
        return query.getResultList();
    }
}

