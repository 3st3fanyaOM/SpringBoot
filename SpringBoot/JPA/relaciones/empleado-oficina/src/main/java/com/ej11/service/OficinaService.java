package com.ej11.service;

import java.util.List;
import java.util.Map;

import com.ej11.model.Oficina;

public interface OficinaService {

	

	public Oficina createOrUpdate(Oficina oficina);

	public Oficina findById(Integer id);

	public List<Oficina> findAll();

	public Integer countEmpleadosByOficina(Integer oficinaId);

	public List<Oficina> findWithMoreThanNEmpleados(Long n);

	public void deleteById(Integer id);

	Map<Integer, Long> getMapaOficinasConEmpleados();

	void updateTelefonoByEmpleadoId(Integer empleadoId, String telefono);
}
