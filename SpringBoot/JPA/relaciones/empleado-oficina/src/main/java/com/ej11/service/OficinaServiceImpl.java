package com.ej11.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ej11.model.Oficina;
import com.ej11.repository.OficinaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OficinaServiceImpl implements OficinaService{

	
	@Autowired
	private OficinaRepository oficinaRepository;	

	
	@Override
    public Oficina createOrUpdate(Oficina oficina) {
        return oficinaRepository.save(oficina);
    }

	@Override
	public Oficina findById(Integer id) {
        return oficinaRepository.findById(id);
    }

	@Override
	public List<Oficina> findAll() {
        return oficinaRepository.findAll();
    }

	@Override
	public Integer countEmpleadosByOficina(Integer oficinaId) {
        return oficinaRepository.findById(oficinaId).getEmpleados().size(); 
    }
    
    
	@Override
	public Map<Integer, Long> getMapaOficinasConEmpleados() {
        return oficinaRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Oficina::getId,
                        oficina -> (long) oficina.getEmpleados().size()
                ));
    }

	@Override
    public List<Oficina> findWithMoreThanNEmpleados(Long n) {
		List<Oficina> ofi =  oficinaRepository.findAll();
		List<Oficina> grandes = new ArrayList<>();
		for (Oficina oficina : ofi) {
			if(oficina.getEmpleados().size()>n) {
				
				grandes.add(oficina);
			}
		}
		return grandes;
    }

	@Override
    public void deleteById(Integer id) {
        oficinaRepository.deleteById(id);
    }        
	
	 @Override
	    public void updateTelefonoByEmpleadoId(Integer empleadoId, String telefono) {
		 oficinaRepository.updateTelefonoByEmpleadoId(empleadoId, telefono);
	    }
}
