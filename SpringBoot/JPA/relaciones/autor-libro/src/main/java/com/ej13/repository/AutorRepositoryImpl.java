package com.ej13.repository;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ej13.model.Autor;
import com.ej13.model.Libro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AutorRepositoryImpl implements AutorRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Autor> getAllAutores() {
		return entityManager.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
	}

	public Autor getAutorById(Integer id) {
		return entityManager.find(Autor.class, id);
	}

	public Libro getLibroById(Integer id) {
		return entityManager.find(Libro.class, id);
	}

	public void addAutor(Autor autor) {
		entityManager.persist(autor);
	}

	public void updateAutor(Autor autor) {
		entityManager.merge(autor);
	}

	public void deleteAutor(Integer id) {
		Autor autor = entityManager.find(Autor.class, id);
		if (autor != null) {
			entityManager.remove(autor);
		}
	}

	public void addLibroToAutor(Autor autor, Libro libro) {
		autor.getLibros().add(libro);
		entityManager.merge(autor);
	}

	public void removeLibroFromAutor(Autor autor, Integer libroId) {
		
		Iterator<Libro> iterator= autor.getLibros().iterator();
		while (iterator.hasNext()) {
            Libro libro = iterator.next();
            if (libro.getId().equals(libroId)) {
                iterator.remove();
                break;
            }
        }
		entityManager.merge(autor);
		
	}

	public List<Libro> getLibrosByAutorId(Integer autorId) {
		Autor autor = entityManager.find(Autor.class, autorId);
		return autor != null ? autor.getLibros() : null;
	}

	public void updateLibroTitulo(Libro libro, String nuevoTitulo) {
		libro.setTitulo(nuevoTitulo);
		entityManager.merge(libro);
	}

	public List<Libro> getAllLibros() {
		return entityManager.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
	}

	public List<Autor> getAutoresByNombreContaining(String nombre) {
		String query = "SELECT a FROM Autor a WHERE a.nombre LIKE :nombre";
		return entityManager.createQuery(query, Autor.class).setParameter("nombre", "%" + nombre + "%").getResultList();
	}

	public void updateAutorNombreByLibroTitulo(String titulo, String nuevoNombre) {
		String query = "SELECT a FROM Autor a JOIN a.libros l WHERE l.titulo = :titulo";
		List<Autor> autores = entityManager.createQuery(query, Autor.class).setParameter("titulo", titulo)
				.getResultList();
		for (Autor autor : autores) {
			autor.setNombre(nuevoNombre);
			entityManager.merge(autor);
		}
	}
}
