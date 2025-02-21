package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ej10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Filtrar números pares y devolver sus cuadrados
        List<Integer> cuadradosPares = numeros.stream()
            .filter(numero -> numero % 2 == 0) // Filtrar solo los números pares
            .map(numero -> numero * numero)     // Calcular el cuadrado
            .collect(Collectors.toList());       // Recoger los resultados en una lista

        // Imprimir los cuadrados de los números pares
        System.out.println(cuadradosPares);
	}

}
