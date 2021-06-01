package com.devsuperior.dscatalog.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		
		
		 List<Product> list = repository.findAll();
		 
		 // Lambda
		 List<ProductDTO> listDTO = list.stream().map( x -> new ProductDTO(x)).collect(Collectors.toList());
		 
		 /*
		    List<ProductDTO> listDTO = new ArrayList<>();
		 
		    for( Product category : list ) {
			 
			 // ProductDTO cDTO = new ProductDTO(category); 
			 // listDTO.add(cDTO);
			 
			 listDTO.add(new ProductDTO(category));
		 }
		 */
		 
		 return listDTO;
	}
	
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		// Mode 1 - get Product converted to Page
		 Page<Product> page = repository.findAll(pageRequest); 
		
		//Mode 2 - Convert list to Page - But don't use because the set is only on moment you create
		 // Then if modify the params on endpoint, don't will modify
		//List<Product> list = repository.findAll();
	    // Page<Product> page = new PageImpl<Product>(list, pageRequest, list.size());
		
	   
		// Convert Product to Product DTO
		 return page.map(x -> new ProductDTO(x));
	}
	
	
	
	
	
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj =  repository.findById(id);

		Product entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
		// ProductDTO idDTO = new ProductDTO(entity);
		return new ProductDTO(entity, entity.getCategories());
	}

	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoEntity(dto, entity);
		entity = repository.save(entity);
		// ProductDTO objDTO = new ProductDTO(entity);
		return new ProductDTO(entity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDtoEntity(dto, entity);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found it: "+id);
		}
		
	}



	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity Violation");
		}

	}
	
	
	private void copyDtoEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear();
		
		for(CategoryDTO catDTO : dto.getCategories()) {
			Category category = categoryRepository.getOne(catDTO.getId());
			entity.getCategories().add(category);
		}
		
	}
	
}

