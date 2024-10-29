package streams;

import java.util.Arrays;
import java.util.stream.Stream;

public class Eje03 {

	public static void main(String[] args) {
		// Dado un array de 5 palabras, imprimir la primera letra de cada palabra.

		String[] palabras = {"Martillo", "Destornillador", "Taladro", "Puntilla", "Pala"};
		Stream<String> streamPalabras = Arrays.stream(palabras);
		streamPalabras.map(n -> n.substring(0,1)).forEach(System.out::println);

	}
}
