package com.ej07.service;

import java.util.List;

import com.ej07.model.Cliente;
import com.ej07.model.Direccion;

public interface ClienteService {

	List<Cliente> getAllClientes();

	Cliente getClienteById(Integer id);

	void saveCliente(Cliente cliente);

	Boolean deleteCliente(Integer id);

	
	Cliente actualizarDireccion(Integer idCliente, Direccion nuevaDireccion);
	
	void actualizarCiudadSevillaNombresA();
	
	void actualizarCiudadPorNombre(String letra, String ciudad);
	
	List<Cliente> findClientesByCiudad(String ciudad);
	
	List<Cliente> findClientesByCiudadB(String ciudad);
	
	List<Direccion> findClientesByCiudadC(String ciudad);
	
}

