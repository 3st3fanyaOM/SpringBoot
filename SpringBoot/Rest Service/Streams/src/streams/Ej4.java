package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ej4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] palabras = {"java", "stream", "ejemplo", "codigo", "programa"};

        // Contar la longitud de cada palabra y devolver en una lista
        List<Integer> longitudes = Arrays.stream(palabras)
                                         .map(String::length)  // Obtener la longitud de cada palabra
                                         .collect(Collectors.toList());  // Recoger los resultados en una lista

        // Imprimir las longitudes
        System.out.println(longitudes);
	}

}
