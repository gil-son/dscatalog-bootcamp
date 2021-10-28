package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.services.validation.UserUpdateValid;

@UserUpdateValid // Annotation created at package services.validation and will use to verify the email
public class UserUpdateDTO extends UserDTO{
	private static final long serialVersionUID = 1L;
	
}
