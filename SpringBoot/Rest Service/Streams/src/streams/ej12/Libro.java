package streams.ej12;

public class Libro{
    private String titulo;
    private String autor;
    private int numeroPaginas;
    private String genero;

    public Libro(String titulo, String autor, int numeroPaginas, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getGenero() {
        return genero;
    }

   

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", genero='" + genero + '\'' +
                '}';
    }
}