package fr.afpa.formation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.afpa.formation.entity.SujetContent;

public class SujetContentValider implements Validator {

	public boolean supports(Class<?> clazz) {
		return SujetContent.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SujetContent sujetContent = (SujetContent) target;
//		if (sujetContent.getCommentaire() == null) {
//			errors.rejectValue("commentaire", "required", "commentaire required");
//		}

	}

}
