package com.ej01.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ej01.model.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

@Repository
public class ClienteRepositoryImpl {

	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Cliente> getClientes() {		
		
		Query<Cliente> query = (Query<Cliente>) entityManager.createQuery("select c from Cliente c", Cliente.class);
		List<Cliente> lista = query.getResultList();
		return lista;		
	}

	public Cliente getCliente(Integer id) {
		
		Cliente cliente = entityManager.find(Cliente.class, id);
		return cliente;
	}

	public Cliente insertaCliente(Cliente cliente) {
		
		//es necesario @transaccional en el servicio		
	    entityManager.persist(cliente);
	    System.out.println(cliente);
	    return cliente;
		
	}

	
	public Boolean deleteCliente(Integer id) {
		
		// necesario @transaccional en el servicio.
		Cliente c = getCliente(id);
		if (c != null) {
            entityManager.remove(c);
            return true;
        }
		return false;
		
		
	}
	
	
	public Cliente actualizarCliente(Cliente cliente) {
		
		return entityManager.merge(cliente);	
		
	}	
	
	
	public List<Cliente> getClientesNombre(String nombre){
			
		
		// Crear la consulta JPQL
        String jpql = "SELECT c FROM Cliente c WHERE c.nombre LIKE :nombre";
        
        // Crear la consulta
        Query<Cliente> query = (Query<Cliente>) entityManager.createQuery(jpql, Cliente.class);
        
        // Configurar el parÃ¡metro
        query.setParameter("nombre", "%" + nombre + "%");
        
        // Ejecutar la consulta y obtener la lista de clientes
        return query.getResultList();
        
        
	}

}
