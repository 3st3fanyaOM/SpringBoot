package com.ej03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej03.model.Vehiculo;
import com.ej03.repository.VehiculoRepository;

import jakarta.transaction.Transactional;

@Service
public class VehiculoService  {

	@Autowired
    private VehiculoRepository vehiculoRepository;

	@Transactional
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.crearVehiculo(vehiculo);
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return vehiculoRepository.obtenerTodosLosVehiculos();
    }

    public Vehiculo obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.obtenerVehiculoPorId(id);
    }

    @Transactional
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculoDetalles) {
        Vehiculo vehiculo = vehiculoRepository.obtenerVehiculoPorId(id);
        
        if (vehiculo!=null) {
            
            vehiculo.setMarca(vehiculoDetalles.getMarca());
            vehiculo.setModelo(vehiculoDetalles.getModelo());
            vehiculo.setAnyo(vehiculoDetalles.getAnyo());
            vehiculo.setMatricula(vehiculoDetalles.getMatricula());
            vehiculo.setEstado(vehiculoDetalles.getEstado());
            vehiculo.setKilometraje(vehiculoDetalles.getKilometraje());
            return vehiculoRepository.actualizarVehiculo(vehiculo);
        }
        
        return null;
    }

    @Transactional
    public boolean eliminarVehiculo(Long id) {
        return vehiculoRepository.eliminarVehiculo(id);
    }

    public List<Vehiculo> consultarVehiculosPorEstado(String estado) {
        return vehiculoRepository.consultarVehiculosPorEstado(estado);
    }

    public List<Vehiculo> filtrarVehiculosPorAnio(Integer startYear, Integer endYear) {
        return vehiculoRepository.filtrarVehiculosPorAnio(startYear, endYear);
    }

    @Transactional
    public Vehiculo actualizarEstado(Long id, String estado) {
        Vehiculo vehiculo = vehiculoRepository.obtenerVehiculoPorId(id);
        if (vehiculo!=null) {            
            vehiculo.setEstado(estado);
            return vehiculoRepository.actualizarVehiculo(vehiculo);
        }
        return null;
    }

    @Transactional
    public Vehiculo registrarKilometraje(Long id, Double kilometraje) {
        Vehiculo vehiculo = vehiculoRepository.obtenerVehiculoPorId(id);
        
        if(vehiculo!=null) {
            vehiculo.setKilometraje(kilometraje);
            return vehiculoRepository.actualizarVehiculo(vehiculo);
        }
        return null;        
    }
}
