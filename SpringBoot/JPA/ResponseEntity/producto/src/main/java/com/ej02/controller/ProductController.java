package com.ej02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ej02.model.Product;
import com.ej02.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createdProduct = productService.createProduct(product);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product productDetails) {
		productDetails.setId(id);
		Product updatedProduct = productService.updateProduct(productDetails);
		if (updatedProduct != null) {
			return ResponseEntity.ok(updatedProduct);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
		boolean isDeleted = productService.deleteProduct(id);
		if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/buscar/{name}")
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
		List<Product> products = productService.getProductsByName(name);
		if(!products.isEmpty())
			return ResponseEntity.ok(products);
		else
			return ResponseEntity.noContent().build();
	}

	
	
	@GetMapping("/price/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @PathVariable float minPrice,
            @PathVariable float maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }
	
	@PostMapping("/bloque")
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        List<Product> createdProducts = productService.createProducts(products);
        return ResponseEntity.noContent().build();
    }
}
