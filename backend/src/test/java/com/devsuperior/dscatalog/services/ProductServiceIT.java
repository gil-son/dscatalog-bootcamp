package com.devsuperior.dscatalog.services;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

// In Integration Test, the application will access database
@SpringBootTest
public class ProductServiceIT { // Integration Test

	private ProductService service;
	private ProductRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;
	
	ProductServiceIT(ProductService service){
		this.service = service;
	}
	
	ProductServiceIT(ProductRepository repository){
		this.repository = repository;
	}
	
	@BeforeEach
	void setUp() throws Exception{
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}
	
	
	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		
		service.delete(existingId);
		Assertions.assertEquals(countTotalProducts - 1, repository.count());
		
	}
	
	@Test
	public void deleteShouldResourceNotFoundExceptionWhenIdDoesNotExists() {
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		
	});

	}
}
