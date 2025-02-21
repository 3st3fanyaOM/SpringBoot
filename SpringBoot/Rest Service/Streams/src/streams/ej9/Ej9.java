package streams.ej9;

import java.util.Arrays;
import java.util.List;

public class Ej9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Persona> personas = Arrays.asList(new Persona("Ana", 30), new Persona("Pedro", 20),
				new Persona("Luis", 28), new Persona("Carlos", 26), new Persona("Alberto", 22));

		// Filtrar personas mayores de 25 años y ordenar por nombre
		personas.stream().filter(persona -> persona.edad > 25).sorted((p1, p2) -> p1.nombre.compareTo(p2.nombre))
				.forEach(System.out::println);
	}

}
