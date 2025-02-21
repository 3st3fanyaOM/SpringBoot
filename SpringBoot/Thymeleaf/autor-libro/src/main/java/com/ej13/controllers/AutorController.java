package com.ej13.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ej13.model.Autor;
import com.ej13.model.Libro;
import com.ej13.service.AutorService;


@Controller
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	
	@GetMapping
	public String obtenerAutores(Model model) {
	    List<Autor> autores = autorService.getAllAutores();
	    model.addAttribute("autores", autores);
	    return "autores-lista";
	}
	
	@GetMapping("/nuevo-autor")
	public String mostrarFormularioDeAutor(Model model) {
	    model.addAttribute("autor", new Autor());
	    return "autor-formulario";
	}

	@PostMapping("/nuevo-autor")
	public String agregarAutor(@ModelAttribute Autor autor) {
	    autorService.addAutor(autor);
	    return "redirect:/autores";
	}
	
	@GetMapping("/nuevo-libro")
	public String mostrarFormularioDeLibro(Model model) {
	    model.addAttribute("libro", new Libro());
	    model.addAttribute("autores", autorService.getAllAutores());
	    return "libro-formulario";
	}

	@PostMapping("/nuevo-libro")
	public String agregarLibro(@RequestParam Integer autorId ,@ModelAttribute Libro libro) {
		System.out.println(autorId);
		autorService.addLibroToAutor(autorId, libro);
	    return "redirect:/autores";
	}
	
	 @GetMapping("/buscar-autores")
	    public String buscarAutores(@RequestParam(name = "nombre", required = false) String nombre, Model model) {
	        List<Autor> autores = new ArrayList<>();
	        
	        if (nombre != null && !nombre.isEmpty()) {
	            autores = autorService.getAutoresByNombreContaining(nombre);
	        }

	        model.addAttribute("autores", autores);
	        model.addAttribute("nombre", nombre);

	        return "buscar-autores"; // Nombre del archivo HTML para mostrar resultados
	    }
	
}
