package com.examenA.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examenA.modelo.Entrada;
import com.examenA.repositorio.EntradaRepositorio;

@Service
public class EntradaServicio {

    @Autowired
    private EntradaRepositorio entradaRepository;

    // A) Crear una nueva entrada
    @Transactional
    public Entrada crearEntrada(Entrada entrada) {
        // Calcular el precio total
        double precioTotal = calcularPrecioTotal(entrada);
        		
        entrada.setPrecioTotal(precioTotal);

        // Guardar la entrada
        entradaRepository.crearEntrada(entrada);
        return entrada;
    }
    
    // Función para calcular el precio total
    public double calcularPrecioTotal(Entrada entrada) {
        double precioTotal = entrada.getPrecioPorEntrada() * entrada.getNumeroEntradas();
        
        // Si se compró por internet, aplicar un 2% extra al precio por entrada
        if (!entrada.getTaquilla()) {
            precioTotal *= 1.02; // Incremento del 2%
        }
        
        return precioTotal;
    }

    // B) Obtener todas las entradas
    public List<Entrada> obtenerEntradas() {
        return entradaRepository.obtenerEntradas();
    }

    // C) Actualizar una entrada
    @Transactional
    public Entrada actualizarEntrada(Long id, Entrada entrada) {
        Entrada entradaExistente = entradaRepository.obtenerEntradaPorId(id);
        if (entradaExistente == null) {
            return null; // No existe la entrada
        }

        // Actualizar los campos
        entradaExistente.setNombreComprador(entrada.getNombreComprador());
        entradaExistente.setPelicula(entrada.getPelicula());
        entradaExistente.setHorario(entrada.getHorario());
        entradaExistente.setNumeroEntradas(entrada.getNumeroEntradas());
        entradaExistente.setPrecioPorEntrada(entrada.getPrecioPorEntrada());
        entradaExistente.setTaquilla(entrada.getTaquilla());


        // Persistir los cambios
        entradaRepository.actualizarEntrada(entradaExistente);
        return entradaExistente;
    }

    // D) Obtener entradas no taquilla
    public List<Entrada> obtenerEntradasNoTaquilla() {
        return entradaRepository.obtenerEntradasNoTaquilla();
    }

    // E) Actualizar el número de entradas
    @Transactional
    public Entrada actualizarNumeroEntradas(Long id, Entrada entrada) {
        Entrada entradaExistente = entradaRepository.obtenerEntradaPorId(id);
        if (entradaExistente == null) {
            return null; // No existe la entrada
        }

        // Solo actualizar el número de entradas
        entradaExistente.setNumeroEntradas(entrada.getNumeroEntradas());

        // Calcular el precio total
        double precioTotal = calcularPrecioTotal(entradaExistente);      
        entradaExistente.setPrecioTotal(precioTotal);

        // Persistir los cambios
        entradaRepository.actualizarEntrada(entradaExistente);
        return entradaExistente;
    }

    // F) Eliminar entradas por comprador
    @Transactional
    public Integer eliminarEntradasPorComprador(String nombreComprador) {
        return entradaRepository.eliminarEntradasPorComprador(nombreComprador);
        
    }

    // G) Modificar película y horario de una entrada
    @Transactional
    public Entrada modificarEntrada(Long id, String pelicula, String horario) {
        Entrada entradaExistente = entradaRepository.obtenerEntradaPorId(id);
        if (entradaExistente == null) {
            return null; // No existe la entrada
        }

        // Actualizar película y horario
        entradaExistente.setPelicula(pelicula);
        entradaExistente.setHorario(horario);

        // Persistir los cambios
        entradaRepository.actualizarEntrada(entradaExistente);
        return entradaExistente;
    }

    // H) Obtener mapa de clientes con número de entradas en taquilla
    public Map<String, Integer> obtenerMapaClientes() {
        List<Entrada> resultados = entradaRepository.obtenerEntradas();

        Map<String, Integer> mapaClientes = new HashMap<>();

        // Recorrer las entradas y actualizar el mapa solo si la compra fue realizada en taquilla
        for (Entrada entrada : resultados) {
            if (entrada.getTaquilla()) {  // Si la compra fue en taquilla
                // Actualizar el número de entradas del cliente en el mapa
                mapaClientes.put(entrada.getNombreComprador(),
                    mapaClientes.getOrDefault(entrada.getNombreComprador(), 0) + 1);
                
            }
        }

        return mapaClientes;
    }

    // I) Obtener la entrada más cara
    public Entrada obtenerEntradaMasCara() {
    	// Si no hay entradas, devolver null
    	List<Entrada> entradas = entradaRepository.obtenerEntradas();
    	if (entradas.isEmpty()) {
            return null;
        }

        // Inicializamos la entrada con el precio más bajo posible
        Entrada entradaMasCara = entradas.get(0);

        // Recorremos las entradas y buscamos la de mayor precioTotal
        for (Entrada entrada : entradas) {
            if (entrada.getPrecioTotal() > entradaMasCara.getPrecioTotal()) {
                entradaMasCara = entrada;
            }
        }

        return entradaMasCara;
    }

    // J) Obtener entradas con más de X entradas
    public List<String> obtenerEntradasConMasDeXEntradas(int numEntradas) {
    	List<Entrada> todasLasEntradas = entradaRepository.obtenerEntradas();

        // Filtramos las entradas según los criterios y mostramos todas las que cumplen
        List<Entrada> entradasFiltradas = todasLasEntradas.stream()
                .filter(e -> e.getNumeroEntradas() > numEntradas && Boolean.TRUE.equals(e.getTaquilla()))
                .toList();

        // Mostrar todas las que cumplen por consola
        entradasFiltradas.forEach(System.out::println);

        // Devolver solo las primeras 2 entradas en formato requerido usando toString()
        return entradasFiltradas.stream()
                .limit(2)
                .map(Entrada::toString)
                .toList();
    }
}