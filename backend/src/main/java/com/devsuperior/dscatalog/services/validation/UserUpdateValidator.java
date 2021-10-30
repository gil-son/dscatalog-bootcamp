package com.devsuperior.dscatalog.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {
	
	
	@Autowired
	private HttpServletRequest request; // Contains the information from request
	
	
	@Autowired
	UserRepository userRepository;
	
	@Override // Logic used when initialize the object
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {
		
		// In Http, all is string and this case is key and value, then is use the Cast
		
		@SuppressWarnings("unchecked") // remove the yellow mark
		var uri = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); // Get a dictionary with attributes
		long userId = Long.parseLong(uri.get("id"));
		
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Put your validation tests here, adding FieldMessage objects to the list
		
		User user = userRepository.findByEmail(dto.getEmail());
		
		if(user != null && user.getId() != userId) {
			list.add(new FieldMessage("email","The email already exist!"));
		}
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation(); // Inserted errors
		}
		return list.isEmpty(); // erro(s) > 0 = true
	}
}

