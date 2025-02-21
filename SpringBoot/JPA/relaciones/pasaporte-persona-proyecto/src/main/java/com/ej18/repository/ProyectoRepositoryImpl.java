package com.ej18.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej18.model.Pasaporte;
import com.ej18.model.Persona;
import com.ej18.model.Proyecto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class ProyectoRepositoryImpl implements ProyectoRepository {

    @PersistenceContext
    private EntityManager entityManager;
 // Métodos CRUD para Persona
    @Override
    public void guardarPersona(Persona persona) {
        entityManager.persist(persona);
    }
    
    @Override
    public void guardarProyecto(Proyecto proyecto) {
        entityManager.persist(proyecto);
    }
    
    public void guardarPasaporte(Pasaporte pasaporte) {
        entityManager.persist(pasaporte);
    }
    
    @Override
    public Persona buscarPersonaPorId(Long id) {
        return entityManager.find(Persona.class, id);
    }
    @Override
    public Pasaporte buscarPasaportePorId(Long id) {
        return entityManager.find(Pasaporte.class, id);
    }
    
    @Override
    public Proyecto buscarProyectoPorId(Long id) {
        return entityManager.find(Proyecto.class, id);
    }
    
    
    @Override
	public void asignarProyectoAPersona(Persona persona, Proyecto proyecto) {
    	Persona p = buscarPersonaPorId(persona.getId());
		Proyecto pr = buscarProyectoPorId(proyecto.getId());

		if (p != null && pr != null) {
			p.agregarProyecto(pr);	
			System.out.println(p.getId());
			System.out.println(pr.getId());
			entityManager.merge(p);
		}    
    }
    
    @Override
	@Transactional
	public void eliminarProyectoDePersona(Persona persona, Proyecto proyecto) {

		Persona p = buscarPersonaPorId(persona.getId());
		Proyecto pr = buscarProyectoPorId(proyecto.getId());
		if (p != null && pr != null) {
			p.eliminarProyecto(pr);
			entityManager.merge(p);
		}
	}
    
    @Override	
	public void asignarPasaporteAPersona(Persona persona, Pasaporte pasaporte) {

    	Persona p = buscarPersonaPorId(persona.getId());
		Pasaporte pas = buscarPasaportePorId(pasaporte.getId());

		if (p != null && pas != null) {
			pasaporte.setPersona(persona);
				
			entityManager.merge(pasaporte);
		}
	}
    
    @Override
    public void actualizarPersona(Persona persona) {
    	System.out.println(persona.getId());
        entityManager.merge(persona);
    }
    
    @Override
    public void actualizarPasaporte(Pasaporte pas) {

        entityManager.merge(pas);
    }
    
        
    @Override
    public List<Persona> obtenerTodasLasPersonas() {
        return entityManager.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
    }
    

}
