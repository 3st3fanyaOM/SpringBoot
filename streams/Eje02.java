package streams;

import java.util.Arrays;
import java.util.List;

public class Eje02 {

	public static void main(String[] args) {
		// Dada una lista de 5 nombres, filtrar e imprimir aquellos que comienzan por
		// ‘A’, convertirlos
		// a mayúsculas y luego ordenarlo alfabéticamente.

		List<String> nombres = Arrays.asList("Ana", "Pedro", "Pilar", "Juan", "Amalia");
		nombres.stream().filter(n -> n.startsWith("A")).map(n -> n.toUpperCase()).sorted().forEach(System.out::println);

	}
}
