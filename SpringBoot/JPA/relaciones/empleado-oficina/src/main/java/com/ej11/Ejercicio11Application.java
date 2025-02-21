package com.ej11;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ej11.model.Empleado;
import com.ej11.model.Oficina;
import com.ej11.service.EmpleadoService;
import com.ej11.service.OficinaService;

@SpringBootApplication
public class Ejercicio11Application  implements CommandLineRunner {
	
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired	
	private OficinaService oficinaService;
	
	public static void main(String[] args){

		SpringApplication.run(Ejercicio11Application.class, args);
	}
	
	public void run(String... args) throws Exception {
		
		  // Pruebas para la entidad Oficina
        System.out.println("Prueba 1: Crear oficinas...");
        Oficina oficina1 = new Oficina("Oficina Central", "123456789");
        Oficina oficina2 = new Oficina("Oficina Norte", "987654321");
        
    
        Empleado empleado1 = new Empleado("Juan Pérez", "Desarrollador", "juanperez@email.com", oficina1);
        Empleado empleado2 = new Empleado("Maria López", "Gerente", "marialopez@email.com", oficina1);
        Empleado empleado3 = new Empleado("Carlos Gómez", "Tester", "carlosgomez@email.com", oficina2);
       
        oficina1.getEmpleados().add(empleado1);
        oficina1.getEmpleados().add(empleado2);
        oficina2.getEmpleados().add(empleado3);
        
        oficinaService.createOrUpdate(oficina1); // Guardar oficina con empleados
        oficinaService.createOrUpdate(oficina2);

        
        System.out.println("Prueba 2: Consultar todas las oficinas...");
        oficinaService.findAll().forEach(oficina -> System.out.println(oficina));

        System.out.println("Prueba 3: Consultar una oficina por ID...");
        Oficina oficinaConsultada = oficinaService.findById(oficina1.getId());
        
        for (Empleado emp : oficinaConsultada.getEmpleados()) {
			System.out.println(emp);
		}
        
        System.out.println("Oficina consultada: " + oficinaConsultada);
        
      
        // Pruebas para la entidad Empleado
        System.out.println("Prueba 5: consultar todo...");
        oficinaService.findAll().forEach(oficina -> System.out.println(oficina));

        // Ahora podemos hacer las pruebas que dependen de los empleados

        System.out.println("Prueba 6: Contar empleados en una oficina...");
        long empleadosOficinaCentral = oficinaService.countEmpleadosByOficina(oficina1.getId());
        System.out.println("Número de empleados en Oficina 1: " + empleadosOficinaCentral);

        System.out.println("Prueba 7: Mapear oficinas con número de empleados...");
        oficinaService.getMapaOficinasConEmpleados().forEach((id, count) -> 
            System.out.println("Oficina ID: " + id + ", Número de empleados: " + count));

        System.out.println("Prueba 8: Oficinas con más de N empleados...");
        oficinaService.findWithMoreThanNEmpleados(1L).forEach(System.out::println);

        System.out.println("Prueba 9: Eliminar una oficina...");
        oficinaService.deleteById(oficina2.getId());
        System.out.println("Oficina con ID " + oficina2.getId() + " eliminada.");

        System.out.println("Prueba 10: Consultar todos los empleados...");
        empleadoService.findAll().forEach(System.out::println);
      
        
        System.out.println("Prueba 11: Consultar un empleado por ID...");
        
        
        Empleado empleadoConsultado = empleadoService.findById(empleado1.getId());
        System.out.println("Empleado consultado: " + empleadoConsultado);

        System.out.println("Prueba 12: Actualizar un empleado...");
        
        empleadoConsultado.setPuesto("Senior Developer");
        empleadoService.createOrUpdate(empleadoConsultado);
        System.out.println("Empleado actualizado: " + empleadoConsultado);

        System.out.println("Prueba 13: Eliminar un empleado...");
        Empleado empleadoEliminar = empleadoService.findById(oficinaConsultada.getEmpleados().get(1).getId());
        empleadoService.deleteById(empleadoEliminar.getId());
        System.out.println("Empleado con ID " + empleadoEliminar.getId() + " eliminado.");

        System.out.println("Prueba 14: Empleados con puesto específico...");
        empleadoService.findByPuesto("Desarrollador").forEach(System.out::println);

        System.out.println("Prueba 15: Actualizar el teléfono de la oficina de un empleado...");
        oficinaService.updateTelefonoByEmpleadoId(empleadoConsultado.getId(), "333333333");        
        
    }

}
