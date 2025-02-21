package streams;

import java.time.LocalDate;
import java.util.stream.Stream;

public class Ej13 {
	 public static void main(String[] args) {
	       
		  // Generar un Stream que produzca la cadena "ABC" cinco veces
	        Stream<String> cadenas = Stream.generate(() -> "ABCD")
	                                       .limit(5) // Limitar a 5 repeticiones
	                                       .map(cadena -> cadena.replace("B", " ")); // Sustituir "B" por un espacio

	        // Imprimir las cadenas resultantes
	        cadenas.forEach(System.out::print);
	    }

}
