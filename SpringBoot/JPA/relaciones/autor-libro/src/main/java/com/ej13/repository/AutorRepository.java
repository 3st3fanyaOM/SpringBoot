package com.ej13.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej13.model.Autor;
import com.ej13.model.Libro;

import jakarta.transaction.Transactional;

@Repository
public interface AutorRepository {

	List<Autor> getAllAutores();

	Autor getAutorById(Integer id);
	
	Libro getLibroById(Integer id);

	void addAutor(Autor autor);

	void updateAutor(Autor autor);

	void deleteAutor(Integer id);

	void addLibroToAutor(Autor autor, Libro libro);

	void removeLibroFromAutor(Autor autor, Integer libroId);

	List<Libro> getLibrosByAutorId(Integer autorId);

	void updateLibroTitulo(Libro libro, String nuevoTitulo);

	List<Libro> getAllLibros();

	List<Autor> getAutoresByNombreContaining(String nombre);

	void updateAutorNombreByLibroTitulo(String titulo, String nuevoNombre);
}
