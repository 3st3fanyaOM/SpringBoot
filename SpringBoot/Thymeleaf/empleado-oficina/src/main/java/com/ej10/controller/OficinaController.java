package com.ej10.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.ej10.model.Empleado;
import com.ej10.model.Oficina;
import com.ej10.service.EmpleadoService;
import com.ej10.service.OficinaService;

@Controller
@RequestMapping("/oficinas")
public class OficinaController {
	
	 @Autowired
	 private  OficinaService  oficinaService ;
	 @Autowired
	 private  EmpleadoService  empleadoService ;

	 @GetMapping
	    public String findAll(Model model) {
	        model.addAttribute("oficinas", oficinaService.findAll());
	        return "oficinas-lista";
	    }
	 
	 @GetMapping("/nuevo")
	    public String mostrarFormularioNuevaOficina(Model model) {
	        model.addAttribute("oficina", new Oficina());
	        model.addAttribute("empleados", empleadoService.findAll()); // Lista de empleados para los checkboxes
	        return "oficina-form"; // Nombre del formulario
	    }
	 
	   @GetMapping("/{id}")
	    public String findById(@PathVariable Integer id, Model model) {
		   
		   model.addAttribute("oficinas", oficinaService.findAll());
	        
	        if (id != null) {
	            Oficina oficina = oficinaService.findById(id);
	            model.addAttribute("empleados", oficina.getEmpleados());
	        }

	        return "oficinas-lista";		   
	    }
	 
	 
	   @PostMapping
	   public String guardarOficina(@ModelAttribute Oficina oficina, @RequestParam List<Integer> empleadosSeleccionados) {
		    
	       List<Empleado> lista = new ArrayList<>();
		   // Relacionar los empleados seleccionados con la oficina
		   for (Integer id : empleadosSeleccionados) {
			   Empleado e = empleadoService.findById(id);
			   lista.add(e);
		   }
		    
		     oficina.setEmpleados(lista);
		     oficinaService.createOrUpdate(oficina);

		     return "redirect:/oficinas"; // Redirige al listado de oficinas
		   
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
