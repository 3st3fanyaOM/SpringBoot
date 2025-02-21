package com.ej07;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ej07.model.Cliente;
import com.ej07.model.Direccion;
import com.ej07.service.ClienteService;



@SpringBootApplication
public class Ejercicio07Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio07Application.class, args);
	}

	@Autowired
	ClienteService clienteService;

	@Override
	public void run(String... args) throws Exception {

		// 1. Insertar el cliente con nombre Pepe  Ruiz y dirección calle sol de
		// Oviedo.
		System.out.println("****************1*********************");
		Cliente cliente1 = new Cliente();
		cliente1.setNombre("Pepe Ruiz");

		Direccion dir1 = new Direccion();
		dir1.setCalle("Sol");
		dir1.setCiudad("Oviedo");
		dir1.setClient(cliente1); //IMP
		cliente1.setDireccion(dir1);
		clienteService.saveCliente(cliente1);

		// 2. Insertar el cliente con nombre Andres Ramirez y dirección calle mar de
		// Cadiz.
		System.out.println("****************2*********************");
		Cliente cliente2 = new Cliente();
		cliente2.setNombre("Andrés Ramírez");

		Direccion dir2 = new Direccion();
		dir2.setCalle("Mar");
		dir2.setCiudad("Cadiz");
		dir2.setClient(cliente2); //IMP
		cliente2.setDireccion(dir2);
		clienteService.saveCliente(cliente2);
		System.out.println("****************3*********************");
		// 3. Mostrar la lista de clientes
		mostrarClientes();

		// 4. Obtener los datos del cliente con el id de Pepe Ruiz.
		System.out.println("****************4*********************");
		System.out.println(clienteService.getClienteById(cliente1.getId()));

		// 5. Dado el id de Pepe Ruiz, actualizar sus datos: Pepe  Jose Ruiz, calle
		// Luna de Madrid.
		System.out.println("****************5*********************");
		cliente1.setNombre("Pepe Jose Ruiz");
		cliente1.getDireccion().setCalle("Luna");
		cliente1.getDireccion().setCiudad("Madrid");
		clienteService.saveCliente(cliente1);

		// 6. Mostrar la lista de clientes
		System.out.println("****************6*********************");
		mostrarClientes();
		
		//7.	Volver a añadir a otro cliente como Andrés Ramírez.
		System.out.println("****************7*********************");
		Cliente cliente3 = new Cliente();
		cliente3.setNombre("Andrés Ramírez");

		Direccion dir3 = new Direccion();
		dir3.setCalle("Mar");
		dir3.setCiudad("Cadiz");
		dir3.setClient(cliente3); //IMP
		cliente3.setDireccion(dir3);
		clienteService.saveCliente(cliente3);
		System.out.println("****************7.1*********************");
		mostrarClientes();
		//8.	Modificar la dirección del primer Andrés: Calle Estrella de Málaga, usando el método 6.
		System.out.println("****************8*********************");
		Direccion d = new Direccion();
		d.setCalle("Estrella");
		d.setCiudad("Málaga");
		d.setClient(cliente2); //IMP
		clienteService.actualizarDireccion(cliente2.getId(), d);
		
		mostrarClientes();
		System.out.println("****************9*********************");
		//9.	Actualizar a Sevilla todos los clientes cuyo nombre empiece por ‘A’ o ‘a’
		clienteService.actualizarCiudadSevillaNombresA();
		mostrarClientes();
		
		//10.	Actualizar a Granada todos los clientes cuyo nombre empiece por P. Comprobar
		System.out.println("****************10*********************");
		clienteService.actualizarCiudadPorNombre("P","Granada");
		mostrarClientes();
		System.out.println("****************11*********************");
		//11.	Mostrar la lista de todos los clientes de Sevilla.
		List<Cliente> clSevilla = clienteService.findClientesByCiudad("Sevilla");
		for (Cliente sevilla : clSevilla) {
			System.out.println(sevilla);
		}
		
		System.out.println("****************12*********************");
		//12.	Eliminar al último Andrés insertado.
		clienteService.deleteCliente(cliente3.getId());
		
		//13.	Mostrar de nuevo la lista de clientes.
		System.out.println("****************13*********************");
		mostrarClientes();
		
	}

	private void mostrarClientes() {

		List<Cliente> clientes = clienteService.getAllClientes();
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}

}
