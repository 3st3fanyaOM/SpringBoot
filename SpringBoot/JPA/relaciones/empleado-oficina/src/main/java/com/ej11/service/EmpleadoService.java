package com.ej11.service;

import java.util.List;

import com.ej11.model.Empleado;

public interface EmpleadoService {

	public Empleado createOrUpdate(Empleado empleado);

	public Empleado findById(Integer id);

	public List<Empleado> findAll();

	public List<Empleado> findByPuesto(String puesto);

	//public List<Empleado> findWithoutOficina();

	public void deleteById(Integer id);

}
