package streams;

import java.util.Arrays;

public class Ej5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] palabras = {"java", "stream", "ejemplo", "codigo", "programa"};

        // Filtrar palabras que tienen más de 5 caracteres
        Arrays.stream(palabras)
                                                           .filter(palabra -> palabra.length() > 5)  // Filtrar palabras con más de 5 caracteres
                                                           .forEach(System.out::println);  
	}

}
