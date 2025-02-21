package com.ej07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ej07.model.Cliente;
import com.ej07.model.Direccion;
import com.ej07.repository.ClienteRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	
	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente getClienteById(Integer id) {
		return clienteRepository.findById(id);
	}

	@Override
	public void saveCliente(Cliente cliente) {

		clienteRepository.save(cliente);
	}

	@Override
	public Boolean deleteCliente(Integer id) {
		Cliente cliente = clienteRepository.findById(id);
		if (cliente != null) {
			clienteRepository.delete(cliente);
			return true;
		}
		return false;
	}

	
	// cambios respecto al 6
	@Override
	public Cliente actualizarDireccion(Integer idCliente, Direccion nuevaDireccion) {

		Cliente cliente = clienteRepository.findById(idCliente);
		if (cliente != null) {
			if (nuevaDireccion != null) {
				nuevaDireccion.setId(cliente.getDireccion().getId());
				nuevaDireccion.setClient(cliente); //IMP
				cliente.setDireccion(nuevaDireccion);
				
				clienteRepository.save(cliente);
				return cliente;
			}
		}
		return null;
	}
	
	 
	
	
	@Override
    public void actualizarCiudadSevillaNombresA() {
        List<Cliente> clientes = clienteRepository.buscarNombresA();
        for (Cliente cliente : clientes) {
            if (cliente.getDireccion() != null) {
                cliente.getDireccion().setCiudad("Sevilla");
                clienteRepository.save(cliente);
            }
        }
    }

	@Override
    public void actualizarCiudadPorNombre(String letra, String ciudad) {
        List<Cliente> clientes = clienteRepository.buscarNombresPorLetra(letra);
        System.out.println(clientes.size());
        for (Cliente cliente : clientes) {
            if (cliente.getDireccion() != null) {
                cliente.getDireccion().setCiudad(ciudad);
                clienteRepository.save(cliente);
            }
        }
    }
	
	@Override
	public List<Cliente> findClientesByCiudad(String ciudad) {
        return clienteRepository.findByCiudad(ciudad);
    }
	
	@Override
	public List<Cliente> findClientesByCiudadB(String ciudad) {
        return clienteRepository.findByCiudadB(ciudad);
    }
	
	
	@Override
	public List<Direccion> findClientesByCiudadC(String ciudad) {
        return clienteRepository.findByCiudadC(ciudad);
    }
	
	
}
