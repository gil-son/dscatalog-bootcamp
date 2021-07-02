package com.devsuperior.dscatalog.services;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repository;
	
	private long existingId;
	private long nonExistingId;
	
	@BeforeEach
	void setUp() throws Exception{
		existingId = 1L;
		nonExistingId = 1000L;
		
//		Mockito.doNothing()
		doNothing().when(repository).deleteById(existingId);
//		Mockito.doThrow
		doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessWhenIdDoesExist() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});
		
//		Mockito.verify()    Mockito.times(1)
		verify(repository, times(1)).deleteById(nonExistingId);
	}
	
	@Test
	public void deleteShouldNothingWhenExists() {
		Assertions.assertDoesNotThrow(() -> {
			service.delete(existingId);
		});
//		Mockito.verify()    Mockito.times(1)
		verify(repository, times(1)).deleteById(existingId);
	}
	
}
