package streams;

import java.util.Arrays;

public class Ej6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

	        // Contar cuántos son pares
	        long conteoPares = Arrays.stream(numeros)
	                                 .filter(num -> num % 2 == 0)
	                                 .count();

	        System.out.println("Cantidad de números pares: " + conteoPares);
	}

}
