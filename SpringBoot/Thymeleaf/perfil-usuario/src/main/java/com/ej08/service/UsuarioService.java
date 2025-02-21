package com.ej08.service;

import java.util.List;

import com.ej08.model.Perfil;
import com.ej08.model.Usuario;

public interface UsuarioService {

	List<Usuario> getAllUsuarios();

	Usuario getUsuarioById(Integer id);

	void saveUsuario(Usuario usuario); // inserta y actualiza

	Boolean deleteUsuario(Integer id);
		
	void actualizarPerfilUsuario(Integer id, Perfil perfil);
	
	Perfil getPerfil(Integer id);
	
	void actualizarEstadoDisponibleUsuario(Integer id);
	
	List<Usuario> usuariosBiografia(String palabra);
	
	Usuario primerUsuarioDisponible();
	
	List<Usuario> usuariosNoDisponibles();
	
	void actualizarUsuariosTodosDisponibles();	
}

