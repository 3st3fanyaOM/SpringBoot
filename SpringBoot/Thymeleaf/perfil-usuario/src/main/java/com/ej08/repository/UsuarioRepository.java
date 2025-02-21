package com.ej08.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej08.model.Perfil;
import com.ej08.model.Usuario;

@Repository
public interface UsuarioRepository {

	List<Usuario> getAllUsuarios();

	Usuario getUsuarioById(Integer id);

	void saveUsuario(Usuario usuario); // inserta y actualiza

	void deleteUsuario(Usuario usuario);
		

	List<Usuario> usuariosBiografia(String palabra);
	
	Usuario primerUsuarioDisponible();
	
	List<Usuario> usuariosNoDisponibles();
	
	
	
}


