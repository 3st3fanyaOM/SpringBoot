package com.examenB.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "tipo_habitacion", nullable = false)
    private String tipoHabitacion;

    @Column(name = "numero_noches", nullable = false)
    private Integer numeroNoches;

    @Column(name = "precio_habitacion", nullable = false)
    private Double precioHabitacion;

    @Column(name = "activa")
    private Boolean activa = true;

    @Column(name = "precio_total")
    private Double precioTotal;

    @Column(name = "codigo_venta", nullable = false)
    private Integer codigoVenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public Integer getNumeroNoches() {
		return numeroNoches;
	}

	public void setNumeroNoches(Integer numeroNoches) {
		this.numeroNoches = numeroNoches;
	}

	public Double getPrecioHabitacion() {
		return precioHabitacion;
	}

	public void setPrecioHabitacion(Double precioHabitacion) {
		this.precioHabitacion = precioHabitacion;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public Double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Integer getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(Integer codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	@Override
    public String toString() {
        return "Reserva ID: " + id +
               " – Número de noches: " + numeroNoches +
               " – Código venta: " + codigoVenta;
    }
}
