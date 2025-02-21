package com.ej01.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para verficar palíndromos
 */
@RestController
public class PalindromoController {
	
	/**
	 * Endopoint para verificar si una palabra es un palíndromo.
	 * @param word la palabra a verificar.
	 * @return un mensaje indicando si la palabra es un palíndromo o no.
	 */
	@GetMapping("/validar-palindromo/{word}")
	public String Palindromo(@PathVariable String word) {
		
		String mensaje = "La palabra " + word;
		if (isPalindromo(word))
			return mensaje + " es un palíndromo";
		else
			return mensaje + " NO es un palíndromo";
				
	}
	
	/**
	 * Método para verificar si una palabra es un palíndromo
	 * @param word la palabra a verificar.
	 * @return true si la palabra esu un palíndromo, false en caso contrario.
	 */
	private boolean isPalindromo(String word) {
		
		String letras []=word.split("");
		// Creamos una cadena vacía para almacenar la versión invertida
        String palabraInvertida = "";

        // Recorremos el array de caracteres desde el final al principio
        for (int i = letras.length - 1; i >= 0; i--) {
            palabraInvertida += letras[i];  // Concatenamos los caracteres en orden inverso
        }

        // Comparamos la palabra original con la invertida
        return word.equals(palabraInvertida);
		
		//otra forma:
		/*
		int length = word.length();
		
		for(int i=0; i<length /2; i++) {
			if(word.charAt(i) != word.charAt(length - i - 1)){
				return false;
			}
		}
		
		return true;
		*/
	}
	

}
