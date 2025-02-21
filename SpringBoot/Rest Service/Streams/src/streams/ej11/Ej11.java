package streams.ej11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Ej11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Curso> cursos = Arrays.asList(new Curso("Curso de Java", 6, 55, 300),
				new Curso("Curso de Python", 1.5, 25, 150), new Curso("Curso de JavaScript", 2.5, 60, 400),
				new Curso("Curso de C++", 7, 70, 200), new Curso("Curso de Ruby", 4, 45, 600));

		// 1. Obtener la cantidad de cursos con una duración mayor a 5 horas.
		long cantidadMayores5Horas = cursos.stream().filter(curso -> curso.getDuracion() > 5).count();
		System.out.println("Cursos con duración mayor a 5 horas: " + cantidadMayores5Horas);

		// 2. Obtener la cantidad de cursos con una duración menor a 2 horas.
		long cantidadMenores2Horas = cursos.stream().filter(curso -> curso.getDuracion() < 2).count();
		System.out.println("Cursos con duración menor a 2 horas: " + cantidadMenores2Horas);

		// 3. Listar el título de todos aquellos cursos con una cantidad de vídeos mayor
		// a 50.
		List<String> cursosConMasDe50Videos = cursos.stream().filter(curso -> curso.getNumeroDeVideos() > 50)
				.map(Curso::getNombre).collect(Collectors.toList());
		System.out.println("Cursos con más de 50 videos: " + cursosConMasDe50Videos);

		// 4. Mostrar en consola el título de los 3 cursos con mayor duración.
		List<Curso> tresCursosMayorDuracion = cursos.stream()
				.sorted((c1, c2) -> Double.compare(c2.getDuracion(), c1.getDuracion())).limit(3)
				.collect(Collectors.toList());
		System.out.println("Los 3 cursos con mayor duración:");
		tresCursosMayorDuracion.forEach(System.out::println);

		// 5. Mostrar en consola la duración total de todos los cursos.
		
		
		double duracionTotal = cursos.stream().map(Curso::getDuracion).reduce(0.0, Double::sum); // Sumar usando reduce		
		System.out.println("Duración total de todos los cursos: " + duracionTotal + " horas");


		// 6. Mostrar en consola la duración de todos aquellos cursos que tengan una
		// cantidad de alumnos inscritos menor a 500.
		System.out.println("Duraciones de cursos con menos de 500 alumnos:");
		cursos.stream().filter(curso -> curso.getNumeroDeAlumnos() < 500)
				.map(curso -> curso.getNombre() + ": " + curso.getDuracion() + " horas").forEach(System.out::println);

		// 7. Obtener el curso con mayor duración.
		List <Curso> cursosordenados = cursos.stream().sorted((c1, c2) -> Double.compare(c2.getDuracion(), c1.getDuracion())).collect(Collectors.toList());
		System.out.println("Curso con mayor duración: " + cursosordenados.get(0));

		// 8. Crear una lista de Strings con todos los títulos de los cursos.
		List<String> titulosCursos = cursos.stream().map(Curso::getNombre).collect(Collectors.toList());
		System.out.println("Títulos de los cursos: " + titulosCursos);
	}
}
