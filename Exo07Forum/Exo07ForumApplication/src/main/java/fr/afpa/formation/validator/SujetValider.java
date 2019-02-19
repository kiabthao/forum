package fr.afpa.formation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.afpa.formation.entity.Sujet;

public class SujetValider implements Validator {

	public boolean supports(Class<?> clazz) {
		return Sujet.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Sujet sujet = (Sujet) target;
		if (sujet.getName() == null || sujet.getName().isEmpty()) {
			errors.rejectValue("name", "required", "name required");
		}

	}

}
