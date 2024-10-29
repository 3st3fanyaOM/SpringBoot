package streams;

import java.util.Arrays;
import java.util.List;

public class Eje01 {

	public static void main(String[] args) {
		// Dada una lista de 5 nombres,
		// filtrar e imprimir aquellos que comienzan por ‘A’

		List<String> nombres = Arrays.asList("Ana", "Pedro", "Pilar", "Juan");
		nombres.stream().filter(n -> n.startsWith("A")).forEach(System.out::println);

	}
}
