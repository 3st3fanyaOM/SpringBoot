package com.ej07.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej07.model.Cliente;
import com.ej07.model.Direccion;

@Repository
public interface ClienteRepository {

	List<Cliente> findAll();

	Cliente findById(Integer id);

	void save(Cliente cliente);

	void delete(Cliente cliente);

	
	List<Cliente> buscarNombresA();
	
	List<Cliente> buscarNombresPorLetra(String letra);
	
	List<Cliente> findByCiudad(String ciudad);
	
	List<Cliente> findByCiudadB(String ciudad);
	
	List<Direccion> findByCiudadC(String ciudad);
	
}


