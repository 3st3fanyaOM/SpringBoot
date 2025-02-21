package com.examenA.repositorio;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.examenA.modelo.Entrada;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EntradaRepositorio {

    @PersistenceContext
    private EntityManager entityManager;

    // Crear una nueva entrada
   
    public Entrada crearEntrada(Entrada entrada) {
        entityManager.persist(entrada);
        return entrada;
    }

    // Obtener todas las entradas
    public List<Entrada> obtenerEntradas() {
        return entityManager.createQuery("SELECT e FROM Entrada e", Entrada.class).getResultList();
    }

 // Obtener una entrada por ID
    public Entrada obtenerEntradaPorId(Long id) {
        return entityManager.find(Entrada.class, id);
    }

    // Obtener las entradas no adquiridas en taquilla
    public List<Entrada> obtenerEntradasNoTaquilla() {
        return entityManager.createQuery("SELECT e FROM Entrada e WHERE e.taquilla = false", Entrada.class)
                .getResultList();
    }

    // Actualizar una entrada
  
    public Entrada actualizarEntrada(Entrada entrada) {
    	 return entityManager.merge(entrada);
       
    }

    // Eliminar entrada por comprador
  
    public Integer eliminarEntradasPorComprador(String nombreComprador) {
        List<Entrada> entradas = entityManager.createQuery("SELECT e FROM Entrada e WHERE e.nombreComprador = :nombre", Entrada.class)
                .setParameter("nombre", nombreComprador)
                .getResultList();
        Integer cont=0;
        for (Entrada entrada : entradas) {
        	entityManager.remove(entrada);
        	cont++;
		}
        return cont;
     
    }

    // Obtener entradas con número de entradas mayor a {numEntradas}
    public List<Entrada> obtenerEntradasConMasDeXEntradas(Integer numEntradas) {
        return entityManager.createQuery("SELECT e FROM Entrada e WHERE e.numeroEntradas > :numEntradas AND e.taquilla = true", Entrada.class)
                .setParameter("numEntradas", numEntradas)
                .getResultList();
    }
}

