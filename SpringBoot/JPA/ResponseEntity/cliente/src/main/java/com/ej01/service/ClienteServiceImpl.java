package com.ej01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ej01.model.Cliente;

import com.ej01.repository.ClienteRepositoryImpl;

import jakarta.transaction.Transactional;

@Service
public class ClienteServiceImpl{

	
	@Autowired
	private ClienteRepositoryImpl cliente;
	
	//@Transactional
	
	public List<Cliente> getClientes() {
		return cliente.getClientes();
	}

	//@Transactional
	
	public Cliente getCliente(Integer id) {
		return cliente.getCliente(id);
	}

	@Transactional
	
	public Cliente insertaCliente(Cliente cl) {
		return cliente.insertaCliente(cl);
		
	}

	@Transactional
	
	public Boolean deleteCliente(Integer id) {
		return cliente.deleteCliente(id);
		
	}
	
	@Transactional
	
	public Cliente actualizarCliente(Cliente cl) {
		return cliente.actualizarCliente(cl);
	}
	
	
	public List<Cliente> getClientesNombre(String nombre){
		List<Cliente> clientes= cliente.getClientesNombre(nombre);
		
		return clientes;
	}
}
