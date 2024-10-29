package streams;

import java.util.stream.Stream;

public class Eje05 {

	public static void main(String args[]) {
		//Dado un array de 5 palabras, devolver aquellas 
		//palabras que tengan más de 5 caracteres.
		
		String[] palabras = { "Amarillo", "Rojo", "Azul", "Naranja", "Rosa" };
		
		Stream<String> palabrasMasDe5 = Stream.of(palabras);
		
		palabrasMasDe5.filter(n->n.length()>5).forEach(System.out::println);
		
	}
}
