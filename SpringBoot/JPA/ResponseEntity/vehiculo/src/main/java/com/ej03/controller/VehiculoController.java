package com.ej03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ej03.model.Vehiculo;
import com.ej03.service.VehiculoService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoService.crearVehiculo(vehiculo);
        return ResponseEntity.ok(nuevoVehiculo); 
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerTodosLosVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerTodosLosVehiculos();
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(vehiculos); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Long id) {
        
    	Vehiculo v = vehiculoService.obtenerVehiculoPorId(id);     	
    	return v == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(v);                
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculoDetalles) {
        
        Vehiculo vehiculoActualizado = vehiculoService.actualizarVehiculo(id, vehiculoDetalles);
        
        return vehiculoActualizado == null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(vehiculoActualizado);
    
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
      
        if(vehiculoService.eliminarVehiculo(id)) {
        	return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/estado/{nuevoEstado}")
    public ResponseEntity<Vehiculo> actualizarEstado(@PathVariable Long id, @PathVariable String nuevoEstado) {
     
        Vehiculo vehiculoActualizado = vehiculoService.actualizarEstado(id, nuevoEstado);
        if(vehiculoActualizado!=null)
        	return ResponseEntity.ok(vehiculoActualizado);
        else
            return ResponseEntity.notFound().build();
        
    }

    @PutMapping("/{id}/kilometraje/{nuevoKilometraje}")
    public ResponseEntity<Vehiculo> registrarKilometraje( @PathVariable Long id, @PathVariable Double nuevoKilometraje) {
        
        Vehiculo vehiculoActualizado = vehiculoService.registrarKilometraje(id, nuevoKilometraje);
        if(vehiculoActualizado!=null)
        	return ResponseEntity.ok(vehiculoActualizado); 
        else
            return ResponseEntity.notFound().build();        
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Vehiculo>> consultarVehiculosPorEstado(@PathVariable String estado) {
        List<Vehiculo> vehiculos = vehiculoService.consultarVehiculosPorEstado(estado);
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(vehiculos);
    }

    @GetMapping("/anio/{startYear}/{endYear}")
    public ResponseEntity<List<Vehiculo>> filtrarVehiculosPorAnio(@PathVariable Integer startYear, @PathVariable Integer endYear) {
        List<Vehiculo> vehiculos = vehiculoService.filtrarVehiculosPorAnio(startYear, endYear);
        if (vehiculos.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(vehiculos); 
    }
}

