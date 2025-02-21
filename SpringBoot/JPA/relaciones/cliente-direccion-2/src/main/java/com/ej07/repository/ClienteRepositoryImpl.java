package com.ej07.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ej07.model.Cliente;
import com.ej07.model.Direccion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

    public List<Cliente> findAll() {
        return entityManager.createQuery("FROM Cliente", Cliente.class).getResultList();
    }

    public Cliente findById(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    public void save(Cliente cliente) {
    	
        if (cliente.getId() == null) {
        	
            entityManager.persist(cliente);
            System.out.println("ha guardado?");
        } else {
            entityManager.merge(cliente);
        }
    }

    public void delete(Cliente cliente) {
    	
        entityManager.remove(cliente);
    }

	
	@Override
	public List<Cliente> buscarNombresA(){
		 return entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :prefix", Cliente.class)
         .setParameter("prefix", "A" + "%")
         .getResultList();
	}
	
	@Override
	public List<Cliente> buscarNombresPorLetra(String letra){
		
		System.out.println("la letra es " + letra);
		 return entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :prefix", Cliente.class)
        .setParameter("prefix", letra + "%")
        .getResultList();
	}
	
	@Override
	 public List<Cliente> findByCiudad(String ciudad) {
		
	        String query = "SELECT c FROM Cliente c WHERE c.direccion.ciudad = :ciudad";
	        return entityManager.createQuery(query, Cliente.class)
	                            .setParameter("ciudad", ciudad)
	                            .getResultList();
	    
		
	    }
	
	@Override
	 public List<Cliente> findByCiudadB(String ciudad) {		
	    
	        String query = "SELECT c FROM Cliente c JOIN c.direccion d WHERE d.ciudad = :ciudad";
	        return entityManager.createQuery(query, Cliente.class)
	                            .setParameter("ciudad", ciudad)
	                            .getResultList();
	    }
	
	@Override
	 public List<Direccion> findByCiudadC(String ciudad) {		
	    
	        String query = "SELECT c FROM Direccion c WHERE c.ciudad = :ciudad";
	        return entityManager.createQuery(query, Direccion.class)
	                            .setParameter("ciudad", ciudad)
	                            .getResultList();
	   }
	    
}
