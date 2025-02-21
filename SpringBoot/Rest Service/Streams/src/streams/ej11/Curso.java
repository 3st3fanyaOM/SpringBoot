package streams.ej11;

public class Curso {
	String nombre;
    Double duracion; // en horas
    Integer numeroDeVideos;
    Integer numeroDeAlumnos;

    public Curso(String nombre, double duracion, int numeroDeVideos, int numeroDeAlumnos) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.numeroDeVideos = numeroDeVideos;
        this.numeroDeAlumnos = numeroDeAlumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDuracion() {
        return duracion;
    }

    public int getNumeroDeVideos() {
        return numeroDeVideos;
    }

    public int getNumeroDeAlumnos() {
        return numeroDeAlumnos;
    }

    @Override
    public String toString() {
        return nombre + " (" + duracion + " horas)";
    }
}
