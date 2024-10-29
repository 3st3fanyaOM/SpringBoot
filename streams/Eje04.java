package streams;

import java.util.stream.Stream;

public class Eje04 {

	public static void main(String[] args) {
		// Dado un array de 5 palabras, devuelve una lista
		// con la longitud de cada palabra. Imprime la
		// lista.

		String[] palabras = { "Martillo", "Destornillador", "Taladro", "Puntilla", "Pala" };
		Stream<String> longPalabras = Stream.of(palabras);

		longPalabras.map(n -> n.length()).toList().forEach(n -> System.out.println(n));

	}
}
