package fr.afpa.formation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import fr.afpa.formation.entity.Commentaire;

public class CommentaireValider implements Validator {

	public boolean supports(Class<?> clazz) {
		return Commentaire.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Commentaire commentaire = (Commentaire) target;
//		if (commentaire.getText() == null || commentaire.getText().isEmpty()) {
//			errors.rejectValue("text", "required", "text required");
//		}

	}

}
