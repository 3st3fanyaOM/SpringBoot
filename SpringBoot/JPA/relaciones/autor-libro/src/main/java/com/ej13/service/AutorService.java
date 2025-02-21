package com.ej13.service;

import java.util.List;

import com.ej13.model.Autor;
import com.ej13.model.Libro;

import jakarta.transaction.Transactional;

public interface AutorService {

	List<Autor> getAllAutores();

	Autor getAutorById(Integer id);

	void addAutor(Autor autor);

	void updateAutor(Autor autor);

	void deleteAutor(Integer id);

	void addLibroToAutor(Integer autorId, Libro libro);

	void removeLibroFromAutor(Integer autorId, Integer libroId);

	List<Libro> getLibrosByAutorId(Integer autorId);

	void updateLibroTitulo(Integer libroId, String nuevoTitulo);

	List<Libro> getAllLibros();

	List<Autor> getAutoresByNombreContaining(String nombre);

}
