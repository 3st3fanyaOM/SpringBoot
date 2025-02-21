package streams;

import java.util.Arrays;

public class Ej7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

	        // Multiplicar todos los elementos
	        int producto = Arrays.stream(numeros)
	                             .reduce(1, (a, b) -> a * b);

	        System.out.println("Producto total: " + producto);
	}

}
