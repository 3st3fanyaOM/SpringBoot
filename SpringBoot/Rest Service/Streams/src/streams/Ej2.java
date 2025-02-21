package streams;

import java.util.Arrays;
import java.util.List;

public class Ej2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Dada una lista de 5 nombres, filtrar e imprimir aquellos que comienzan por ‘A’, convertirlos a mayúsculas y luego ordenarlo alfabéticamente.
		
		List<String> nombres = Arrays.asList("Ana", "Pedro", "Andrés", "Carlos", "Alberto");

        // Filtrar, convertir a mayúsculas y ordenar alfabéticamente
        nombres.stream()
               .filter(nombre -> nombre.startsWith("A"))
               .map(String::toUpperCase)
               .sorted()
               .forEach(System.out::println);
		
	}

}
