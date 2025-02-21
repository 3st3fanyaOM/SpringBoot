package com.ej11.controller;

import java.util.List;
import java.util.Map;

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

import com.ej11.model.Empleado;
import com.ej11.model.Oficina;
import com.ej11.service.EmpleadoService;
import com.ej11.service.OficinaService;

@RestController
@RequestMapping("/api/oficinas")
public class OficinaController {
	
	 @Autowired
	 private  OficinaService  oficinaService ;

	 @PostMapping
	    public ResponseEntity<Oficina> create(@RequestBody Oficina oficina) {
	        return ResponseEntity.ok(oficinaService.createOrUpdate(oficina));
	    }

	    @GetMapping
	    public ResponseEntity<List<Oficina>> findAll() {
	        return ResponseEntity.ok(oficinaService.findAll());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Oficina> findById(@PathVariable Integer id) {
	        return ResponseEntity.ok(oficinaService.findById(id));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
	        oficinaService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }

	    @GetMapping("/{id}/empleados/count")
	    public ResponseEntity<Integer> countEmpleadosByOficina(@PathVariable Integer id) {
	        return ResponseEntity.ok(oficinaService.countEmpleadosByOficina(id));
	    }

	    @GetMapping("/empleados/mapa")
	    public ResponseEntity<Map<Integer, Long>> getMapaOficinasConEmpleados() {
	        return ResponseEntity.ok(oficinaService.getMapaOficinasConEmpleados());
	    }

	    @GetMapping("/empleados/mas-de/{n}")
	    public ResponseEntity<List<Oficina>> findWithMoreThanNEmpleados(@PathVariable Long n) {
	        return ResponseEntity.ok(oficinaService.findWithMoreThanNEmpleados(n));
	    }

	    @PutMapping("/{id}/telefono")
	    public ResponseEntity<Void> updateTelefonoByEmpleadoId(@PathVariable Integer id, @RequestBody String telefono) {
	        oficinaService.updateTelefonoByEmpleadoId(id, telefono);
	       
	        return ResponseEntity.noContent().build();
	       
	    }

}
