package com.ej02.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ej02.model.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

@Repository
public class ProductRepositoryImpl  {
	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Product> findAll() {
		return entityManager.createQuery("FROM Product", Product.class).getResultList();
	}

	
	public Product findById(Integer id) {
		return entityManager.find(Product.class, id);
	}

	
	public Product save(Product product) {
		//es necesario @transaccional en el servicio
		if (product.getId() == null) {
			entityManager.persist(product);
			return product;
		} else {
			return entityManager.merge(product);
		}
	}

	
	public Boolean delete(Integer id) {
		Product product = entityManager.find(Product.class, id);
		if (product != null) {
			entityManager.remove(product);
			return true;
		}
		return false;
	}

	
	public Product update(Product productDetails) {
		Product product = entityManager.find(Product.class, productDetails.getId());
		if (product != null) {
			product.setName(productDetails.getName());
			product.setPrice(productDetails.getPrice());
			return entityManager.merge(product);
		}
		return null;
	}

	
	public List<Product> findByNameContaining(String name) {
		String query = "SELECT p FROM Product p WHERE p.name LIKE :name";
		return entityManager.createQuery(query, Product.class).setParameter("name", "%" + name + "%").getResultList();
	}

	
	public List<Product> findByPriceBetween(Float minPrice, Float maxPrice) {
		String query = "SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice";
		return entityManager.createQuery(query, Product.class).setParameter("minPrice", minPrice)
				.setParameter("maxPrice", maxPrice).getResultList();
	}

	
	public List<Product> saveAll(List<Product> products) {

		for (Product product : products) {
			if (entityManager.contains(product)) {
				entityManager.merge(product);
			} else {
				if (product.getId() == null) {
					entityManager.persist(product);
				} else {
					entityManager.merge(product);
				}
			}
		}
		return products;
	}

}
