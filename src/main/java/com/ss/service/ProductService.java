package com.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.model.Product;
import com.ss.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}

	public void addProduct(Product product) {
		productRepository.save(product);
		
	}

	public Product getProductById(int id) {
		return productRepository.findById(id);
	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> homeSearch(String keyword) {
		return productRepository.homeSearch(keyword);
	}
}
