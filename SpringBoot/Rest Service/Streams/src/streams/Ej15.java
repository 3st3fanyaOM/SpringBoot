package streams;

import java.util.List;
import java.util.stream.Stream;

public class Ej15 {
	
	public static void main(String[] args) {
		
		        // Paso 1: Generar una secuencia de los primeros 10 números naturales
		        List<String> numerosComoCadenas = Stream.iterate(4, n -> n + 2) // Inicia desde 1
		                                                .limit(10) // Limita a los primeros 10 números
		                                                .map(n -> "Número: " + n)  // Transforma a cadenas
		                                                .toList(); // Colecta en una lista

		        // Paso 2: Imprimir la lista de cadenas
		        numerosComoCadenas.forEach(System.out::println);
		
	}	

}
