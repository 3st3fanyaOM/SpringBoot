package com.ej06.service;

import java.util.List;

import com.ej06.model.Cliente;
import com.ej06.model.Direccion;

public interface ClienteService {

	List<Cliente> getAllClientes();

	Cliente getClienteById(Integer id);

	void saveCliente(Cliente cliente);

	Boolean deleteCliente(Integer id);

	//Cliente updateCliente(Cliente cliente);

	Cliente actualizarDireccion(Integer idCliente, Direccion nuevaDireccion);
	
	void actualizarCiudadSevillaNombresA();
	
	void actualizarCiudadPorNombre(String letra, String ciudad);
	
	List<Cliente> findClientesByCiudad(String ciudad);
	
	
}

