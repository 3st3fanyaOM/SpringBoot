package com.ej10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ej10.model.Empleado;
import com.ej10.service.EmpleadoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	
	 @Autowired
	 private  EmpleadoService empleadoService;

	 	@GetMapping
	    public String findAll(Model model) {
	    	model.addAttribute("empleados", empleadoService.findAll());
	        return "empleados-lista";
	    }
	 
	 	@GetMapping("/nuevo")
	    public String mostrarFormularioNuevoEmpleado(Model model) {
	        model.addAttribute("empleado", new Empleado());
	        return "empleado-form"; // Formulario para crear empleado
	    }
	 	
	    @PostMapping
	    public String create(@ModelAttribute Empleado empleado) {
	    	 empleadoService.createOrUpdate(empleado);
	         return "redirect:/empleados"; 
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Empleado> findById(@PathVariable Integer id) {
	        return ResponseEntity.ok(empleadoService.findById(id));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
	        empleadoService.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Empleado> updateEmpleado(
	            @PathVariable Integer id,
	            @RequestBody Empleado empleado) {
	        Empleado existingEmpleado = empleadoService.findById(id);
	        if (existingEmpleado == null) {
	            return ResponseEntity.notFound().build();
	        }
	        // Actualizar los campos del empleado
	        existingEmpleado.setNombre(empleado.getNombre());
	        existingEmpleado.setPuesto(empleado.getPuesto());
	        existingEmpleado.setEmail(empleado.getEmail());
	       
	        Empleado updatedEmpleado = empleadoService.createOrUpdate(existingEmpleado);
	        return ResponseEntity.ok(updatedEmpleado);
	    }
	    
	 // **Devolver todos los empleados que tengan un puesto específico**
	    @GetMapping("/puesto/{puesto}")
	    public ResponseEntity<List<Empleado>> getEmpleadosByPuesto(@PathVariable String puesto) {
	        List<Empleado> empleados = empleadoService.findByPuesto(puesto);
	        return ResponseEntity.ok(empleados);
	    }

	   
}
