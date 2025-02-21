package com.examenA.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name = "entradas")
	public class Entrada {
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(nullable = false)
	    private String nombreComprador;
	    
	    @Column(nullable = false)
	    private String pelicula;
	    
	    @Column(nullable = false)
	    private String horario;
	    
	    @Column(nullable = false)
	    private Integer numeroEntradas;
	    
	    @Column(nullable = false)
	    private Double precioPorEntrada;
	    
	    @Column(nullable = false)
	    private Boolean taquilla = true;
	    
	    @Column(nullable = false)
	    private Double precioTotal;

	  
	    public void calcularPrecioTotal() {
	        this.precioTotal = this.precioPorEntrada * this.numeroEntradas;
	        if (!this.taquilla) {
	            this.precioTotal *= 1.02;  // Aumento del 2% si se compra por internet
	        }
	    }


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getNombreComprador() {
			return nombreComprador;
		}


		public void setNombreComprador(String nombreComprador) {
			this.nombreComprador = nombreComprador;
		}


		public String getPelicula() {
			return pelicula;
		}


		public void setPelicula(String pelicula) {
			this.pelicula = pelicula;
		}


		public String getHorario() {
			return horario;
		}


		public void setHorario(String horario) {
			this.horario = horario;
		}


		public Integer getNumeroEntradas() {
			return numeroEntradas;
		}


		public void setNumeroEntradas(Integer numeroEntradas) {
			this.numeroEntradas = numeroEntradas;
		}


		public Double getPrecioPorEntrada() {
			return precioPorEntrada;
		}


		public void setPrecioPorEntrada(Double precioPorEntrada) {
			this.precioPorEntrada = precioPorEntrada;
		}


		public Boolean getTaquilla() {
			return taquilla;
		}


		public void setTaquilla(Boolean taquilla) {
			this.taquilla = taquilla;
		}


		public Double getPrecioTotal() {
			return precioTotal;
		}


		public void setPrecioTotal(Double precioTotal) {
			this.precioTotal = precioTotal;
		}
		
		@Override
		public String toString() {
		    return "Entrada ID: " + id + " – Número de entradas: " + numeroEntradas + " – Película: " + pelicula;
		}
	    
}
