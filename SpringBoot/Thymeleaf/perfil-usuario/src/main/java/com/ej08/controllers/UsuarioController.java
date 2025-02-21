package com.ej08.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ej08.model.Perfil;
import com.ej08.model.Usuario;
import com.ej08.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
    	
    	
    	Usuario usuario = new Usuario();
        usuario.setPerfil(new Perfil());

        model.addAttribute("usuario", usuario);
        
        return "usuario-form";
    }

   
    
    @PostMapping
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
    
        usuarioService.saveUsuario(usuario);
        return "redirect:/usuarios";
    }
    
    @GetMapping("/primerDisponible")
    public String primerDisponible(Model model) {
    	Usuario primer = usuarioService.primerUsuarioDisponible();
        model.addAttribute("usuario",primer);
        return "primerUsuario";
    }
    
    @GetMapping("/{id}")
    public String detalleId(@PathVariable Integer id, Model model) {
    	Usuario usuario = usuarioService.getUsuarioById(id);
        model.addAttribute("usuario",usuario);
        return "usuario-form";
    }
    
/*
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuario_form";
    }

    @PostMapping("/editar")
    public String actualizarUsuario(Usuario usuario) {
        usuarioService.actualizar(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }*/

}
