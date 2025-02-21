package streams;

import java.util.Arrays;

public class Ej3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] palabras = {"hola", "mundo", "java", "stream", "api"};

        Arrays.stream(palabras)
              .map(palabra -> palabra.substring(0, 1))
              .forEach(System.out::println);
	}

}
