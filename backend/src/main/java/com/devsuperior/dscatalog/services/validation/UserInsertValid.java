package com.devsuperior.dscatalog.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UserInsertValidator.class) // This class will implement UserInsertValid
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)


// This interface is use to create annotation (boilerplate code)
public @interface UserInsertValid {
	String message() default "Validation error";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
