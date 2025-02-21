package com.ej11.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ej11.model.Empleado;
import com.ej11.model.Oficina;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class OficinaRepositoryImpl implements OficinaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Oficina save(Oficina oficina) {
		if (oficina.getId() == null) {
			entityManager.persist(oficina);
			return oficina;
		} else {
			return entityManager.merge(oficina);
		}
	}

	@Override
	public Oficina findById(Integer id) {
		return entityManager.find(Oficina.class, id);
	}

	@Override
	public List<Oficina> findAll() {
		return entityManager.createQuery("FROM Oficina", Oficina.class).getResultList();
	}

	/*@Override
	public Long countEmpleadosByOficina(Integer oficinaId) {
		return entityManager.createQuery("SELECT COUNT(e) FROM Empleado e WHERE e.oficina.id = :oficinaId", Long.class)
				.setParameter("oficinaId", oficinaId).getSingleResult();
		
	*/

	/*
	@Override
	public List<Oficina> findWithMoreThanNEmpleados(Long n) {
		return entityManager.createQuery(
				"SELECT o FROM Oficina o WHERE (SELECT COUNT(e) FROM Empleado e WHERE e.oficina.id = o.id) > :n",
				Oficina.class).setParameter("n", n).getResultList();
	}*/

	@Override
	public void deleteById(Integer id) {
		Oficina oficina = entityManager.find(Oficina.class, id);
		if (oficina != null) {
			entityManager.remove(oficina);
		}
	}

	@Override	
	public void updateTelefonoByEmpleadoId(Integer empleadoId, String telefono) {
		
		Empleado empleado = entityManager.find(Empleado.class, empleadoId);
		// Verificar si el empleado existe
        if (empleado != null) {
            // Buscar la oficina asociada al empleado usando el empleado.getOficinaId() (relación One-to-Many)
            String query = "SELECT o FROM Oficina o JOIN o.empleados e WHERE e.id = :empleadoId";
            Oficina oficina = (Oficina) entityManager.createQuery(query)
                    .setParameter("empleadoId", empleadoId)
                    .getSingleResult();

            // Si se encuentra la oficina, actualizamos el teléfono
            if (oficina != null) {
                oficina.setTelefono(telefono);
                entityManager.merge(oficina);  // Persistir la oficina con el nuevo teléfono
                System.out.println("Teléfono de la oficina actualizado correctamente.");
            } else {
                System.out.println("La oficina asociada no se encontró.");
            }
        } else {
            System.out.println("Empleado no encontrado.");
        }
		
	}

	
}
