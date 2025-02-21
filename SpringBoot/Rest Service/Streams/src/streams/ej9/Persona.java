package streams.ej9;

public class Persona {
	String nombre;
	int edad;

	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return nombre + " (" + edad + " años)";
	}
}
