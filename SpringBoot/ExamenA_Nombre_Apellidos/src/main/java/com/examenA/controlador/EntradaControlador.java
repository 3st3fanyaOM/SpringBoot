package com.examenA.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenA.modelo.Entrada;
import com.examenA.servicio.EntradaServicio;

@RestController
@RequestMapping("/api/entradas")
public class EntradaControlador {

    @Autowired
    private EntradaServicio entradaService;

    // A) Crear una nueva entrada
    @PostMapping
    public ResponseEntity<Entrada> crearEntrada(@RequestBody Entrada entrada) {
        Entrada creada = entradaService.crearEntrada(entrada);
        return ResponseEntity.ok(creada); // 200 - OK
    }

    // B) Obtener todas las entradas
    @GetMapping
    public ResponseEntity<List<Entrada>> obtenerEntradas() {
        List<Entrada> entradas = entradaService.obtenerEntradas();
        if (entradas.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 - Sin contenido
        }
        return ResponseEntity.ok(entradas); // 200 - OK
    }

    // C) Actualizar una entrada (por id)
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarEntrada(@PathVariable Long id, @RequestBody Entrada entrada) {
        Entrada existente = entradaService.actualizarEntrada(id, entrada);
        if (existente == null) {
            return ResponseEntity.notFound().build(); // 404 - No encontrado
        }
        return ResponseEntity.noContent().build(); // 204 - Sin contenido (no devuelve la entrada, solo éxito)
    }

    // D) Obtener entradas no taquilla
    @GetMapping("/noTaquilla")
    public ResponseEntity<List<Entrada>> obtenerEntradasNoTaquilla() {
        List<Entrada> entradas = entradaService.obtenerEntradasNoTaquilla();
        if (entradas.isEmpty()) {
            return ResponseEntity.notFound().build(); // 204 - Sin contenido
        }
        return ResponseEntity.ok(entradas); // 200 - OK
    }

    // E) Actualizar el número de entradas
    @PatchMapping("/nuevasEntradas/{id}")
    public ResponseEntity<Entrada> actualizarNumeroEntradas(@PathVariable Long id, @RequestBody Entrada entrada) {
        Entrada entradaModificada = entradaService.actualizarNumeroEntradas(id, entrada);
        if (entradaModificada == null) {
            return ResponseEntity.notFound().build(); // 404 - No encontrado
        }
        return ResponseEntity.ok(entradaModificada); // 200 - OK
    }

    // F) Eliminar entradas por comprador (nombreComprador)
    @DeleteMapping("/{nombreComprador}")
    public ResponseEntity<String> eliminarEntradasPorComprador(@PathVariable String nombreComprador) {
        Integer num= entradaService.eliminarEntradasPorComprador(nombreComprador);
        String result= "Se han borrado " + num + " entradas" ;
        return ResponseEntity.ok(result); // 204 - Sin contenido
    }

    // G) Modificar película y horario de una entrada por id
    @PatchMapping("/{id}/modificarEntrada/{pelicula}/{horario}")
    public ResponseEntity<Entrada> modificarEntrada(@PathVariable Long id, @PathVariable String pelicula, @PathVariable String horario) {
        Entrada entradaModificada = entradaService.modificarEntrada(id, pelicula, horario);
        if (entradaModificada == null) {
            return ResponseEntity.notFound().build(); // 404 - No encontrado
        }
        return ResponseEntity.ok(entradaModificada); // 200 - OK
    }

    // H) Obtener mapa de clientes con número de entradas en taquilla
    @GetMapping("/mapaClientes")
    public ResponseEntity<Map<String, Integer>> obtenerMapaClientes() {
        Map<String, Integer> mapaClientes = entradaService.obtenerMapaClientes();
        if (mapaClientes.isEmpty()) {
            return ResponseEntity.notFound().build(); // 204 - Sin contenido
        }
        return ResponseEntity.ok(mapaClientes); // 200 - OK
    }

    // I) Obtener entrada más cara
    @GetMapping("/masCara")
    public ResponseEntity<Entrada> obtenerEntradaMasCara() {
        Entrada entrada = entradaService.obtenerEntradaMasCara();
        if (entrada == null) {
            return ResponseEntity.notFound().build(); // 404 - No encontrado
        }
        return ResponseEntity.ok(entrada); // 200 - OK
    }

    // J) Obtener entradas con más de {numEntradas} entradas
    @GetMapping("/entradasMultiples/{numEntradas}")
    public ResponseEntity<List<String>> obtenerEntradasMultiples(@PathVariable Integer numEntradas) {
        List<String> entradas = entradaService.obtenerEntradasConMasDeXEntradas(numEntradas);
        if (entradas.isEmpty()) {
            return ResponseEntity.notFound().build(); // 204 - Sin contenido
        }
        return ResponseEntity.ok(entradas); // 200 - OK
    }
}
