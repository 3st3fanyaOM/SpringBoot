package streams;

import java.util.Arrays;
import java.util.List;

public class Ej1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Dada una lista de 5 nombres, filtrar e imprimir aquellos que comienzan por 'A'
		List<String> nombres = Arrays.asList("Ana", "Pedro", "Andrés", "Carlos", "Alberto");

        // Filtrar e imprimir aquellos que comienzan por 'A'
        nombres.stream()
               .filter(nombre -> nombre.startsWith("A"))
               .forEach(System.out::println);
		
	}

}
