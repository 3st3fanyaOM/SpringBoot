package com.ej11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej11.model.Empleado;
import com.ej11.repository.EmpleadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService{

	
	@Autowired
	private EmpleadoRepository empleadoRepository;
  

    public Empleado createOrUpdate(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado findById(Integer id) {
        return empleadoRepository.findById(id);
    }

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public List<Empleado> findByPuesto(String puesto) {
        return empleadoRepository.findByPuesto(puesto);
    }
/*
    public List<Empleado> findWithoutOficina() {
        return empleadoRepository.findWithoutOficina();
    }*/

    public void deleteById(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
