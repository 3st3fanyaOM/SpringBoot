package streams;

import java.util.Random;
import java.util.stream.Stream;

public class Ej14 {
	  public static void main(String[] args) {
	        Random random = new Random();

	        Stream<Integer> numerosParesAleatorios = Stream.generate(() -> random.nextInt(20) + 1) // Genera números entre 1 y 20
	                .filter(n -> n % 2 == 1) // Filtra para obtener solo números pares
	                .distinct() // Evita duplicados
	                .limit(5); // Limita a 5 números

	        numerosParesAleatorios.forEach(System.out::println);
	    }
}
