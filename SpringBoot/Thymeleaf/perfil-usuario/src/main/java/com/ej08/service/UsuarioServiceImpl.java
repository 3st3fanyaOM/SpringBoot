package com.ej08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ej08.model.Perfil;
import com.ej08.model.Usuario;
import com.ej08.repository.UsuarioRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository clienteRepository;

	@Override
	public List<Usuario> getAllUsuarios() {
		return clienteRepository.getAllUsuarios();
	}

	@Override
	public Usuario getUsuarioById(Integer id) {
		return clienteRepository.getUsuarioById(id);
	}

	@Override
	public void saveUsuario(Usuario usuario) {

		clienteRepository.saveUsuario(usuario);

	}

	@Override
	public Boolean deleteUsuario(Integer id) {
		Usuario usuario = clienteRepository.getUsuarioById(id);
		if (usuario != null) {
			clienteRepository.deleteUsuario(usuario);
			return true;
		}
		return false;
	}

	@Override
	public void actualizarPerfilUsuario(Integer id, Perfil nuevoPerfil) {

		Usuario usuario = clienteRepository.getUsuarioById(id);
		if (usuario != null) {
			if (nuevoPerfil != null) {
				nuevoPerfil.setId(usuario.getPerfil().getId());
				usuario.setPerfil(nuevoPerfil);
				clienteRepository.saveUsuario(usuario);

			}
		}

	}

	@Override
	public Perfil getPerfil(Integer id) {
		Usuario usuario = clienteRepository.getUsuarioById(id);
		return usuario.getPerfil();
	}

	@Override
	public void actualizarEstadoDisponibleUsuario(Integer id) {
		Usuario usuario = clienteRepository.getUsuarioById(id);
		usuario.getPerfil().setEstado("DISPONIBLE");
	}

	@Override
	public List<Usuario> usuariosBiografia(String palabra) {

		List<Usuario> lista = clienteRepository.usuariosBiografia(palabra);
		return lista;

	}

	@Override
	public Usuario primerUsuarioDisponible() {
		return clienteRepository.primerUsuarioDisponible();
	}

	@Override
	public List<Usuario> usuariosNoDisponibles() {
		List<Usuario> usuarios = clienteRepository.usuariosNoDisponibles();

		return usuarios;
	}

	@Override
	public void actualizarUsuariosTodosDisponibles() {
		List<Usuario> usuarios = clienteRepository.getAllUsuarios();

		for (Usuario usuario : usuarios) {
			usuario.getPerfil().setEstado("DISPONIBLE");
			clienteRepository.saveUsuario(usuario);
		}

	}

}
