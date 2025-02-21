package com.ej13.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej13.model.Autor;
import com.ej13.model.Libro;
import com.ej13.repository.AutorRepository;

import jakarta.transaction.Transactional;

@Service
public class AutorServiceImpl implements AutorService{

	@Autowired
	private  AutorRepository autorDAO;

	public List<Autor> getAllAutores() {
		return autorDAO.getAllAutores();
	}

	public Autor getAutorById(Integer id) {
		return autorDAO.getAutorById(id);
	}

	@Transactional
	public void addAutor(Autor autor) {
		autorDAO.addAutor(autor);
	}

	@Transactional
	public void updateAutor(Autor autor) {
		autorDAO.updateAutor(autor);
	}

	@Transactional
	public void deleteAutor(Integer id) {
		autorDAO.deleteAutor(id);
	}

	@Transactional
	public void addLibroToAutor(Integer autorId, Libro libro) {
		Autor autor = autorDAO.getAutorById(autorId);
		if (autor != null) {
			autorDAO.addLibroToAutor(autor, libro);
		}
	}

	@Transactional
	public void removeLibroFromAutor(Integer autorId, Integer libroId) {
		Autor autor = autorDAO.getAutorById(autorId);
		if (autor != null) {
			autorDAO.removeLibroFromAutor(autor, libroId);
		}
	}

	public List<Libro> getLibrosByAutorId(Integer autorId) {
		return autorDAO.getLibrosByAutorId(autorId);
	}

	@Transactional
	public void updateLibroTitulo(Integer libroId, String nuevoTitulo) {
		Libro libro = autorDAO.getLibroById(libroId);
		if (libro != null) {
			autorDAO.updateLibroTitulo(libro, nuevoTitulo);
		}
	}

	public List<Libro> getAllLibros() {
		return autorDAO.getAllLibros();
	}

	public List<Autor> getAutoresByNombreContaining(String nombre) {
		return autorDAO.getAutoresByNombreContaining(nombre);
	}

	@Transactional
	public void updateAutorNombreByLibroTitulo(String titulo, String nuevoNombre) {
		autorDAO.updateAutorNombreByLibroTitulo(titulo, nuevoNombre);
	}
}
