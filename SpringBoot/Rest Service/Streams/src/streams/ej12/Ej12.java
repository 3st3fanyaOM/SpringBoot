package streams.ej12;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Ej12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  // 1. Crear una lista de 5 libros
        List<Libro> libros = Arrays.asList(
                new Libro("El Señor de los Anillos", "J.R.R. Tolkien", 1200, "Ficción"),
                new Libro("Cien años de soledad", "Gabriel García Márquez", 400, "Ficción"),
                new Libro("Historia de dos ciudades", "Charles Dickens", 300, "Ficción"),
                new Libro("El Alquimista", "Paulo Coelho", 208, "Ficción"),
                new Libro("Guía del autoestopista galáctico", "Douglas Adams", 224, "Ciencia ficción")
        );

        // 2. Filtrar libros con más de 300 páginas e imprimir
        System.out.println("Libros con más de 300 páginas:");
        libros.stream()
                .filter(libro -> libro.getNumeroPaginas() > 300)
                .forEach(System.out::println);

        // 3. Obtener los títulos de todos los libros e imprimir
        System.out.println("\nTítulos de todos los libros:");
        libros.stream()
                .map(Libro::getTitulo) //.map(libro -> libro.getTitulo())
                .forEach(System.out::println);

        // 4. Contar cuántos libros hay en total
        long totalLibros = libros.stream().count();
        System.out.println("\nTotal de libros: " + totalLibros);

        // 5. Obtener el libro con más páginas
        Optional<Libro> libroConMasPaginas = libros.stream()
                .max((l1, l2) -> l1.getNumeroPaginas()- l2.getNumeroPaginas());
        libroConMasPaginas.ifPresent(libro -> 
                System.out.println("\nEl libro con más páginas es: " + libro));

        // 6. Obtener los títulos que contengan la palabra ‘historia’ independientemente de cómo esté escrita.
        System.out.println("\nTítulos que contienen la palabra 'historia':");
        libros.stream()
                .filter(libro -> libro.getTitulo().toLowerCase().contains("historia"))
                .map(Libro::getTitulo)
                .forEach(System.out::println);

        // 7. Ordenar los resultados por la longitud del título.
        System.out.println("\nLibros ordenados por la longitud del título:");
        libros.stream()
                .sorted((l1, l2) -> l1.getTitulo().length() -l2.getTitulo().length())  //Integer.compare(l1.getTitulo().length(), l2.getTitulo().length()))
                .forEach(System.out::println);

        // 8. Obtener los tres títulos con más páginas
        System.out.println("\nTres libros con más páginas:");
        libros.stream()
                .sorted((l1, l2) -> Integer.compare(l2.getNumeroPaginas(), l1.getNumeroPaginas())) // Orden descendente
                .limit(3)
                .map(Libro::getTitulo)
                .forEach(System.out::println);

        // 9. Obtener los 4 primeros libros que tengan más de 250 páginas y cuyo autor contenga a ‘Michael’
        System.out.println("\nCuatro libros con más de 250 páginas y autor que contiene 'Michael':");
        libros.stream()
                .filter(libro -> libro.getNumeroPaginas() > 250 && libro.getAutor().toLowerCase().contains("michael"))
                .limit(4)
                .forEach(System.out::println);

        // 10. Encuentra el primer libro que tenga exactamente 200 páginas.
        Optional<Libro> libroCon200Paginas = libros.stream()
                .filter(libro -> libro.getNumeroPaginas() == 200)
                .findFirst();
        libroCon200Paginas.ifPresent(libro -> 
                System.out.println("\nEl primer libro con exactamente 200 páginas: " + libro));

        // 11. Encuentra cualquier libro cuyo género sea Aventura.
        Optional<Libro> libroAventura = libros.stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase("Aventura"))
                .findAny();
        libroAventura.ifPresent(libro -> 
                System.out.println("\nCualquier libro de género Aventura: " + libro));

        // 12. Comprobar si todos los libros son de ficción.
        boolean todosSonFiccion = libros.stream()
                .allMatch(libro -> libro.getGenero().equalsIgnoreCase("Ficción"));
        System.out.println("\nTodos los libros son de ficción: " + todosSonFiccion);

        // 13. Comprobar si todos los libros tienen más de 100 páginas.
        boolean todosTienenMasDeCienPaginas = libros.stream()
                .allMatch(libro -> libro.getNumeroPaginas() > 100);
        System.out.println("Todos los libros tienen más de 100 páginas: " + todosTienenMasDeCienPaginas);
   
		
	}

}
