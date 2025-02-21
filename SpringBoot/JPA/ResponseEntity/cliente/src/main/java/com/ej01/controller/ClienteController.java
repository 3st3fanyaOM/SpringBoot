package com.ej01.controller;

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

import com.ej01.model.Cliente;
import com.ej01.service.ClienteServiceImpl;



@RestController
@RequestMapping("/clientes")
public class ClienteController {

	
	@Autowired
    private ClienteServiceImpl servicio;
	
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
    	
    	List<Cliente> clientes = servicio.getClientes();
        return ResponseEntity.ok(clientes);
    }
    
   
	
    @PostMapping
    public ResponseEntity<Cliente> insertaCliente(@RequestBody Cliente cliente) {
    	
    	servicio.insertaCliente(cliente);
    	return ResponseEntity.ok(cliente);    	
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Integer id) {
    	
        Cliente cl =  servicio.getCliente(id);
        if (cl==null) {
        	return ResponseEntity.notFound().build();
        }else {
        	return ResponseEntity.ok(cl);
        }        		
    }
     
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
    	
    	if(servicio.deleteCliente(id)) {
    		
    		return ResponseEntity.noContent().build();
    	}
    
    	return ResponseEntity.notFound().build();
    	
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        // Se asegura de que el ID coincida con el cliente que se va a actualizar
        cliente.setId(id); 
        Cliente clienteActualizado = servicio.actualizarCliente(cliente);
        
        if (clienteActualizado == null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el cliente
        }

        return ResponseEntity.ok(clienteActualizado); // Devuelve el cliente actualizado
    }
   
    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> actualizarClienteParcial(@PathVariable int id, @RequestBody Cliente cliente) {
    	Cliente clienteExistente =  servicio.getCliente(id);
        
        if (clienteExistente==null) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el cliente
        }

        // Actualizar solo los campos proporcionados
        if (cliente.getNombre() != null) {
            clienteExistente.setNombre(cliente.getNombre());
        }
        if (cliente.getApellidos() != null) {
            clienteExistente.setApellidos(cliente.getApellidos());
        }

        Cliente clienteActualizado = servicio.actualizarCliente(clienteExistente);
        return ResponseEntity.ok(clienteActualizado); // Devuelve el cliente actualizado
    }
    
    @GetMapping("/getNombre/{nombre}")
    public ResponseEntity<List<Cliente>> getClientesNombre(@PathVariable String nombre) {
    	
    	
    	List<Cliente> clientes =  servicio.getClientesNombre(nombre);
        

        if (clientes.isEmpty()) {
        	return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(clientes);
    }
    
}
