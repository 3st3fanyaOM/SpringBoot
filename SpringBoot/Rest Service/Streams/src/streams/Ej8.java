package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ej8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 Double[] precios = {15.0, 22.5, 10.0, 35.0, 25.0};

	        // Filtrar precios mayores a 20 euros
	        List<Double> mayoresDe20 = Arrays.stream(precios)
	                                         .filter(precio -> precio > 20)	                                         
	                                         .collect(Collectors.toList());

	        System.out.println(mayoresDe20);
	}

}
