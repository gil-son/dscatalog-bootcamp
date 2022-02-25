package com.devsuperior.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> { // UserInsertDTO will receive the annotation from UserInsertValid. But required the annotation @InsertValid on UserDTO, because have an extends 
	
	
	@Autowired
	UserRepository userRepository;
	
	@Override // Logic used when initialize the object
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Put your validation tests here, adding FieldMessage objects to the list
		
		User user = userRepository.findByEmail(dto.getEmail());
		
		if(user != null) {
			list.add(new FieldMessage("email","The email already exist!"));
		}
		
		
		
		// for each error, is inserid in beans Validation
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation(); // Inserted errors
		}
		return list.isEmpty(); // erro(s) > 0 = false
	}
}

