package com.ej02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ej02.model.Product;

import com.ej02.repository.ProductRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl  {

	@Autowired
	private ProductRepositoryImpl productRepository;

	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	
	public Product getProductById(Integer id) {
		return productRepository.findById(id);
	}

	
	@Transactional
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	
	@Transactional
	public Product updateProduct(Product productDetails) {
		Product product = productRepository.findById(productDetails.getId());
		if (product != null) {
			product.setName(productDetails.getName());
			product.setPrice(productDetails.getPrice());
			return productRepository.save(product);
		}
		return null;
	}
	
	 @Transactional
	    public List<Product> createProducts(List<Product> products) {
	        return productRepository.saveAll(products);
	    }

	
	@Transactional
	public Boolean deleteProduct(Integer id) {

		return productRepository.delete(id);
	}

	
	public List<Product> getProductsByName(String name) {
		return productRepository.findByNameContaining(name);
	}

	
	public List<Product> getProductsByPriceRange(Float minPrice, Float maxPrice) {
		return productRepository.findByPriceBetween(minPrice, maxPrice);
	}
}
