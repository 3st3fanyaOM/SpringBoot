package com.ej06.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ej06.model.Cliente;
import com.ej06.model.Direccion;
import com.ej06.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		List<Cliente> clientes = clienteService.getAllClientes();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Cliente cliente = clienteService.getClienteById(id);
		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Void> saveCliente(@RequestBody Cliente cliente) {

		clienteService.saveCliente(cliente);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
		boolean deleted = clienteService.deleteCliente(id);
		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	@PutMapping
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente clienteDetails) {
		Cliente updatedCliente = clienteService.updateCliente(clienteDetails);
		if (updatedCliente != null) {
			return ResponseEntity.ok(updatedCliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}*/

	@PutMapping("/direccion/{idCliente}")
	public ResponseEntity<Cliente> actualizarDireccion(@PathVariable Integer idCliente,
			@RequestBody Direccion nuevaDireccion) {

		Cliente clienteActualizado = clienteService.actualizarDireccion(idCliente, nuevaDireccion);
		if (clienteActualizado != null) {
			return ResponseEntity.ok(clienteActualizado); // 200 OK
		} else {
			return ResponseEntity.notFound().build(); // 404 Not Found
		}
	}

	@PatchMapping("/actualizar-ciudad")
    public ResponseEntity<Void> actualizarCiudad()
      {
        clienteService.actualizarCiudadSevillaNombresA();
        return ResponseEntity.noContent().build(); // 204 No Content
    }
	
	@PatchMapping("/actualizar-ciudad/{letra}/{ciudad}")
    public ResponseEntity<Void> actualizarCiudadGeneral(@PathVariable String letra, @PathVariable String ciudad)
      {
        clienteService.actualizarCiudadPorNombre(letra,ciudad);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
	
	@GetMapping("/buscarPorCiudad/{ciudad}")
    public ResponseEntity<List<Cliente>> getClientesByCiudad(@PathVariable String ciudad) {
        List<Cliente> clientes = clienteService.findClientesByCiudad(ciudad);
        System.out.println(clientes.size());
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }
	
	
}